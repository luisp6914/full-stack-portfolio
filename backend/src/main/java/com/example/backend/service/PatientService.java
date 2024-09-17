package com.example.backend.service;

import com.example.backend.model.Patient;
import com.example.backend.persistence.PatientEntity;
import com.example.backend.persistence.PatientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;

    }
    //Method to get patient by ID
    public PatientEntity getPatientById(Integer id){
        return patientRepository.getPatientEntityById(id);
    }

    /*Method to get all the patient in DB*/
    public List<PatientEntity> getAllPatients(){
        return patientRepository.findAll();
    }

    //Method to insert new patient to DB
    public Optional<PatientEntity> insertNewPatient(HttpEntity<String> patient){
        Optional<PatientEntity> insertedPatient = Optional.empty();

        //Json input from body is turned into a patient model
        Optional<Patient> patientFromHttpBody = jsonToPatientModel(patient.getBody());

        //TODO check if data is actually valid
        if(patientFromHttpBody.isPresent()){
            PatientEntity newPatient = patientEntityMapper(patientFromHttpBody.get());
            PatientEntity returnedPatient = patientRepository.save(newPatient);
            insertedPatient = Optional.of(returnedPatient);
        }

        return insertedPatient;
    }

    //Private helper methods
    private PatientEntity patientEntityMapper (Patient patient){
        return new PatientEntity(patient.getFirstName(), patient.getLastName(), patient.getVaccineId());
    }

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


    public Optional<PatientEntity> deletePatient(Integer id) {
        Optional<PatientEntity> patientToBeDeleted = patientRepository.findById(id);

        if(patientToBeDeleted.isPresent()){
            patientRepository.deleteById(id);
        }

        return patientToBeDeleted;
    }
}
