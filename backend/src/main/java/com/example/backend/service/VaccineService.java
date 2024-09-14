package com.example.backend.service;

import com.example.backend.model.Vaccine;
import com.example.backend.persistence.VaccineEntity;
import com.example.backend.persistence.VaccineRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {

    VaccineRepository vaccineRepository;

    @Autowired
    public VaccineService(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;

    }

    public VaccineEntity getVaccineById(Integer id){
        return vaccineRepository.getVaccineEntityById(id);

    }

    public List<VaccineEntity> getAllVaccines(){
        return vaccineRepository.findAll();
    }

    public Optional<VaccineEntity> insertNewVaccine(HttpEntity<String> vaccine){
        Optional<VaccineEntity> insertedVaccine = Optional.empty();

        //Json input from body is turned into a vaccine model
        Optional<Vaccine> vaccineFromHttpBody = jsonToVaccineModel(vaccine.getBody());

        //TODO check if data is actually valid
        if(vaccineFromHttpBody.isPresent()){
            VaccineEntity newVaccine = vaccineEntityMapper(vaccineFromHttpBody.get());
            VaccineEntity returnedVaccine = vaccineRepository.save(newVaccine);
            insertedVaccine = Optional.of(returnedVaccine);
        }

        return insertedVaccine;
    }

    public Optional<VaccineEntity> editVaccine(Integer id, HttpEntity<String> vaccine){
        Optional<VaccineEntity> updatedVaccine = Optional.empty();
        Optional<VaccineEntity> oldVaccine = Optional.ofNullable(vaccineRepository.getVaccineEntityById(id));

        if(oldVaccine.isEmpty()){
            return updatedVaccine;
        }

        Optional<Vaccine> vaccineFromHttpBody = jsonToVaccineModel(vaccine.getBody());

        //TODO check if data is actually valid
        if(vaccineFromHttpBody.isPresent()){
            VaccineEntity vaccineToBeEdited = updateVaccine(vaccineFromHttpBody.get(), oldVaccine.get());
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
        return new VaccineEntity(vaccine.getName(), vaccine.getDosesRequired(), vaccine.getDaysBetweenDoses(), vaccine.getTotalDosesReceived());
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

    private VaccineEntity updateVaccine(Vaccine newVaccineInfo, VaccineEntity vaccine){
        if(newVaccineInfo.getName() != null){
            vaccine.setName(newVaccineInfo.getName());
        }
        if(newVaccineInfo.getDaysBetweenDoses() != null){
            vaccine.setDaysBetweenDoses(newVaccineInfo.getDaysBetweenDoses());
        }

        return vaccine;
    }




}
