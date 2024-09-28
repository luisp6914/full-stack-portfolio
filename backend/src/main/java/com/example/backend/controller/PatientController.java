package com.example.backend.controller;

import com.example.backend.dto.PatientWithVaccineInfoDTO;
import com.example.backend.persistence.PatientEntity;
import com.example.backend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/getPatientById")
    public ResponseEntity<PatientWithVaccineInfoDTO> getPatientById(@RequestParam Integer id){
        Optional<PatientWithVaccineInfoDTO> patientDTO = patientService.getPatientById(id);

        if(patientDTO.isPresent()){
            return new ResponseEntity<>(patientDTO.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/getAllPatients")
    public List<PatientWithVaccineInfoDTO> getAllPatient(){
        return patientService.getAllPatients();
    }

    @CrossOrigin(origins = "http://localhost:5173")
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

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/addSecondDose")
    public ResponseEntity<String> addSecondDose(@RequestParam Integer id){
        boolean success = patientService.addSecondVaccine(id);

        if(success){
            return new ResponseEntity<>("Second dose added successfully", HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Failled to add second dose", HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("deletePatient")
    public ResponseEntity<Integer> deletePatient(@RequestParam Integer id){
        Optional<PatientEntity> deletionSuccess = patientService.deletePatient(id);

        Integer patientId = null;
        HttpStatus status = HttpStatus.CONFLICT;

        if(deletionSuccess.isPresent()){
            patientId = deletionSuccess.get().getId();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(patientId, status);
    }


}
