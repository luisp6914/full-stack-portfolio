package com.example.backend.controller;

import com.example.backend.persistence.VaccineEntity;
import com.example.backend.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * This method is to add a vaccine to the DB. It will create an optional insertionSuccess
     * of VaccineEntity type. Use a vaccineService method that will return the optional. If the change was
     * successful, then it will be checked with isPresent method.
     * @param httpEntity the json data in string type
     * @return the vaccine id and  status.
     */
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

    @PutMapping("editVaccine")
    public ResponseEntity<Integer> editVaccine(@RequestParam Integer id, HttpEntity<String> httpEntity){
        Optional<VaccineEntity> insertionSuccess = vaccineService.editVaccine(id, httpEntity);
        Integer vaccineId = null;
        HttpStatus status = HttpStatus.CONFLICT;
        if(insertionSuccess.isPresent()){
            vaccineId = insertionSuccess.get().getId();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(vaccineId, status);
    }
}
