package com.example.backend.controller;

import com.example.backend.persistence.VaccineEntity;
import com.example.backend.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class VaccineController {

    VaccineService vaccineService;

    @Autowired
    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    /**
     * Get method for vaccines based on ID
     * @param id the id of the vaccine
     * @return The vaccine details
     */
    @GetMapping("/vaccine")
    public VaccineEntity getVaccineById(@RequestParam Integer id){
        return vaccineService.getVaccineById(id);
    }

    @GetMapping("/vaccines")
    public List<VaccineEntity> getAllVaccines(){
        return vaccineService.getAllVaccines();
    }

    @PostMapping("/addVaccine")
    public ResponseEntity<Integer> addVaccine(HttpEntity<String> httpEntity){
        Optional<VaccineEntity> insertionSuccess = vaccineService.insertNewVaccine(httpEntity);
        Integer vaccineId = null;
        HttpStatus status = HttpStatus.CONFLICT;
        if(insertionSuccess.isPresent()){
            vaccineId = insertionSuccess.get().getId();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(vaccineId, status);
    }
}
