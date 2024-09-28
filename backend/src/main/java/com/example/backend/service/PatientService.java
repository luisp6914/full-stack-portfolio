package com.example.backend.service;

import com.example.backend.dto.PatientWithVaccineInfoDTO;
import com.example.backend.model.Patient;
import com.example.backend.persistence.PatientEntity;
import com.example.backend.persistence.PatientRepository;
import com.example.backend.persistence.VaccineEntity;
import com.example.backend.persistence.VaccineRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final VaccineRepository vaccineRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, VaccineRepository vaccineRepository) {
        this.patientRepository = patientRepository;
        this.vaccineRepository = vaccineRepository;

    }
    //Method to get patient by ID
    public Optional<PatientWithVaccineInfoDTO> getPatientById(Integer id){
        Optional<PatientEntity> patientEntity = patientRepository.findById(id);

        if(patientEntity.isPresent()){
            //Fetching the patient data
            PatientEntity patient = patientEntity.get();

            //Fetching the vaccine data
            VaccineEntity vaccine = vaccineRepository.getVaccineEntityById(patient.getVaccineId());
            String vaccineName = (vaccine != null) ? vaccine.getName(): "";

            //Mapping patientEntity to DTO
            PatientWithVaccineInfoDTO dto = new PatientWithVaccineInfoDTO(
                    patient.getId(),
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getVaccineId(),
                    vaccineName,
                    patient.getDose1Date(),
                    patient.getDose2Date()
            );
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    /*Method to get all the patient in DB*/
    public List<PatientWithVaccineInfoDTO> getAllPatients(){
        List<PatientEntity> patients = patientRepository.findAll();

        //Map PatientEntity to PatientWithVaccineInfoDTO
        return patients.stream().map(patient -> {
            //Fetch Vaccine based on vaccineId
            VaccineEntity vaccine = vaccineRepository.getVaccineEntityById(patient.getVaccineId());
            Integer vaccineDosesRequired = (vaccine != null) ? vaccine.getDosesRequired() : null;
            String vaccineName = (vaccine != null) ? vaccine.getName() : null;

            //Create a Data Transfer Object
            return new PatientWithVaccineInfoDTO(
                    patient.getId(),
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getVaccineId(),
                    vaccineName,
                    vaccineDosesRequired,
                    patient.getDose1Date(),
                    patient.getDose2Date()
            );
        }).collect(Collectors.toList());
    }

    //Method to insert new patient to DB
    public Optional<PatientEntity> insertNewPatient(HttpEntity<String> patient){
        Optional<PatientEntity> insertedPatient = Optional.empty();

        //Json input from body is turned into a patient model
        Optional<Patient> patientFromHttpBody = jsonToPatientModel(patient.getBody());

        //TODO check if data is actually valid
        if(patientFromHttpBody.isPresent()){
            PatientEntity newPatient = patientEntityMapper(patientFromHttpBody.get());

            Integer vaccineId = newPatient.getVaccineId();
            Optional<VaccineEntity> vaccineEntityOptional = vaccineRepository.findById(vaccineId);

            //Making sure vaccines doses decrease with the addition of a user
            if(vaccineEntityOptional.isPresent()){
                VaccineEntity vaccineEntity = vaccineEntityOptional.get();

                //Check if there are doses remaining
                if (vaccineEntity.getDosesRemaining() > 0){
                    vaccineEntity.setDosesRemaining(vaccineEntity.getDosesRemaining() - 1);
                    vaccineRepository.save(vaccineEntity);
                }
            }

            PatientEntity returnedPatient = patientRepository.save(newPatient);
            insertedPatient = Optional.of(returnedPatient);
        }

        return insertedPatient;
    }

    //Private helper methods
    private PatientEntity patientEntityMapper (Patient patient){
        return new PatientEntity(patient.getFirstName(), patient.getLastName(), patient.getVaccineId());
    }

    /**
     * This method will covert the json data into a Patient model using the ObjectMapper class.
     * @param jsonPatient the patient in json format
     * @return an Optional of Patient.
     */
    private Optional<Patient> jsonToPatientModel(String jsonPatient){
        ObjectMapper mapper = new ObjectMapper();
        Optional<Patient> patient = Optional.empty();

        try{
            Patient mappedPatient = mapper.readValue(jsonPatient, Patient.class);
            patient = Optional.of(mappedPatient);
        } catch (JsonProcessingException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return patient;
    }

    /**
     * Getting rid of a patient
     * @param id the id of the patient to be removed
     * @return the data of the patient that was deleted returns
     */
    public Optional<PatientEntity> deletePatient(Integer id) {
        Optional<PatientEntity> patientToBeDeleted = patientRepository.findById(id);

        if(patientToBeDeleted.isPresent()){
            patientRepository.deleteById(id);
        }

        return patientToBeDeleted;
    }

    /**
     * Adding the second vaccine to the patient.
     * @param patientId the id of the patient
     * @return will return true if the user exist and was able to add the second dose, else the
     * second dose failed and it was not added
     */
    public boolean addSecondVaccine(Integer patientId) {
        //Fetching the patient entity by id
        Optional<PatientEntity> patientEntityOptional = patientRepository.findById(patientId);

        if(patientEntityOptional.isEmpty()){
            return false; //Patient not found
        }

        //Set the current date as the second dose date
        PatientEntity patientEntity = patientEntityOptional.get();
        patientEntity.setDose2Date(LocalDate.now());

        //Save the updated patient entity
        patientRepository.save(patientEntity);

        //Update the vaccine doseRemaining
        Integer vaccineId = patientEntity.getVaccineId();
        Optional<VaccineEntity> vaccineEntityOptional = vaccineRepository.findById(vaccineId);
        if(vaccineEntityOptional.isPresent()){
            VaccineEntity vaccineEntity = vaccineEntityOptional.get();
            vaccineEntity.setDosesRemaining(vaccineEntity.getDosesRemaining() - 1);
            vaccineRepository.save(vaccineEntity);
        }

        return true;

    }
}
