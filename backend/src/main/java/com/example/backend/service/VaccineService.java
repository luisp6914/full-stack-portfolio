package com.example.backend.service;

import com.example.backend.model.Vaccine;
import com.example.backend.persistence.VaccineEntity;
import com.example.backend.persistence.VaccineRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {

    VaccineRepository vaccineRepository;

    @Autowired
    public VaccineService(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;

    }

    /**
     * Method for finding vaccine based on ID
     * @param id the ID of the vaccine
     * @return the data of the vaccine based on ID
     */
    public VaccineEntity getVaccineById(Integer id){
        return vaccineRepository.getVaccineEntityById(id);

    }

    /**
     * Method to fetch all the vaccines
     * @return list of all the vaccines
     */
    public List<VaccineEntity> getAllVaccines(){
        return vaccineRepository.findAll();
    }

    /**
     * Method to add new patient
     * @param vaccine the data of the vaccine in json
     * @return the new vaccine that was added
     */
    public Optional<VaccineEntity> insertNewVaccine(HttpEntity<String> vaccine){
        //Place holder
        Optional<VaccineEntity> insertedVaccine = Optional.empty();

        //Json input from body is turned into a vaccine model using a helper method
        Optional<Vaccine> vaccineFromHttpBody = jsonToVaccineModel(vaccine.getBody());

        //TODO check if data is actually valid
        //Checks if the json was mapped to vaccine model
        if(vaccineFromHttpBody.isPresent()){
            //Creating a new vaccine entity from the json
            VaccineEntity newVaccine = vaccineEntityMapper(vaccineFromHttpBody.get());
            //The returned data from adding the new vaccine
            VaccineEntity returnedVaccine = vaccineRepository.save(newVaccine);
            insertedVaccine = Optional.of(returnedVaccine);
        }

        return insertedVaccine;
    }

    /**
     * Method to edit vaccine name or doses
     * @param id of the vaccine
     * @param vaccine vaccine json data
     * @return returns the updated vaccine
     */
    public Optional<VaccineEntity> editVaccine(Integer id, HttpEntity<String> vaccine){
        //Placeholder for the updated vaccine
        Optional<VaccineEntity> updatedVaccine = Optional.empty();
        //fetches vaccine with set ID
        Optional<VaccineEntity> oldVaccine = Optional.ofNullable(vaccineRepository.getVaccineEntityById(id));

        //If the vaccine does not exist, returns the empty updated vaccine
        if(oldVaccine.isEmpty()){
            return updatedVaccine;
        }
        //Turns the json to a model of vaccine and assigns it to vaccineFromHttpBody
        Optional<Vaccine> vaccineFromHttpBody = jsonToVaccineModel(vaccine.getBody());

        //TODO check if data is actually valid
        //If the vaccine from the body was correctly mapped
        if(vaccineFromHttpBody.isPresent()){
            //uses helper method to update the name or doses of the vaccine
            VaccineEntity vaccineToBeEdited = updateVaccine(vaccineFromHttpBody.get(), oldVaccine.get());
            //saves the updated vaccine to the DB
            VaccineEntity returnedVaccine = vaccineRepository.save(vaccineToBeEdited);
            updatedVaccine = Optional.of(returnedVaccine);
        }

        return updatedVaccine;
     }

    /**
     * Creating a vaccine entity from the vaccine variable
     * @param vaccine the vaccine model
     * @return vaccine entity
     */
    private VaccineEntity vaccineEntityMapper(Vaccine vaccine){
        return new VaccineEntity(vaccine.getName(), vaccine.getDosesRequired(), vaccine.getDoseIntervals(), vaccine.getDosesReceived());
    }

    /**
     * Helper method to take the json date and map it into a vaccine object
     * @param jsonVaccine json data
     * @return vaccine optional
     */
    private Optional<Vaccine> jsonToVaccineModel(String jsonVaccine){
        ObjectMapper mapper = new ObjectMapper();
        Optional<Vaccine> vaccine = Optional.empty();

        try {
            Vaccine mappedVaccine = mapper.readValue(jsonVaccine, Vaccine.class);
            vaccine = Optional.of(mappedVaccine);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return vaccine;
    }

    /**
     * This method updates the name or intervals of the selected vaccine
     * @param newVaccineInfo the new info of the vaccine
     * @param vaccine vaccine entity
     * @return will return the updated vaccine
     */
    private VaccineEntity updateVaccine(Vaccine newVaccineInfo, VaccineEntity vaccine){
        if(newVaccineInfo.getName() != null){
            vaccine.setName(newVaccineInfo.getName());
        }
        if(newVaccineInfo.getDoseIntervals() != null){
            vaccine.setDoseIntervals(newVaccineInfo.getDoseIntervals());
        }

        return vaccine;
    }

    /**
     * Method to add doses to the vaccine with specific id
     * @param vaccineId the id of the vaccine
     * @param dosesToAdd the number of doses to add
     * @return true if added successfully, else failed to add doses
     */
    public boolean addDoses(Integer vaccineId, int dosesToAdd) {
        //Fetch the vaccine entity by ID
        Optional<VaccineEntity> vaccineEntityOptional = vaccineRepository.findById(vaccineId);

        if (vaccineEntityOptional.isEmpty()){
            return false;
        }

        //The actual vaccine data fetched by id
        VaccineEntity vaccineEntity = vaccineEntityOptional.get();

        System.out.println(vaccineEntity.getDosesReceived());
        vaccineEntity.setDosesReceived(vaccineEntity.getDosesReceived() + dosesToAdd);

        System.out.println(vaccineEntity.getDosesRemaining());
        vaccineEntity.setDosesRemaining(vaccineEntity.getDosesRemaining() + dosesToAdd);

        //save the updated vaccine entity
        vaccineRepository.save(vaccineEntity);

        return true;

    }
}
