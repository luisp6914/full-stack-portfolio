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

    private VaccineEntity vaccineEntityMapper(Vaccine vaccine){
        return new VaccineEntity(vaccine.getName(), vaccine.getDosesRequired(), vaccine.getDaysBetweenDoses(), vaccine.getTotalDosesReceived());
    }

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


}
