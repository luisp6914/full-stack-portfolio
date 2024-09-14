package com.example.backend.controller;

import com.example.backend.model.Patient;
import com.example.backend.persistence.PatientEntity;
import com.example.backend.service.PatientService;
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
public class PatientController {
    PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient")
    public PatientEntity getPatientById(@RequestParam Integer id){
        return patientService.getPatientById(id);
    }

    @GetMapping("/patients")
    public List<PatientEntity> getAllPatient(){
        return patientService.getAllPatients();
    }

    @PostMapping("/addPatient")
    public ResponseEntity<Integer> addPatient(HttpEntity<String> httpEntity){
        Optional<PatientEntity> insertionSuccess = patientService.insertNewPatient(httpEntity);
        Integer patientId = null;
        HttpStatus status = HttpStatus.CONFLICT;
        if(insertionSuccess.isPresent()){
            patientId = insertionSuccess.get().getId();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(patientId, status);
    }

}
