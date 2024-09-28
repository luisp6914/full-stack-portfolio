package com.example.backend.dto;

import java.time.LocalDate;

public class PatientWithVaccineInfoDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer vaccineId;
    private String vaccineName;
    private Integer vaccineDosesRequired;
    private LocalDate dose1Date;
    private LocalDate dose2Date;

    public PatientWithVaccineInfoDTO(){}

    public PatientWithVaccineInfoDTO(Integer id, String firstName, String lastName, Integer vaccineId, String vaccineName, Integer vaccineDosesRequired, LocalDate dose1Date, LocalDate dose2Date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
        this.vaccineDosesRequired = vaccineDosesRequired;
        this.dose1Date = dose1Date;
        this.dose2Date = dose2Date;
    }

    public PatientWithVaccineInfoDTO(Integer id, String firstName, String lastName, Integer vaccineId, String vaccineName, LocalDate dose1Date, LocalDate dose2Date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
        this.dose1Date = dose1Date;
        this.dose2Date = dose2Date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(Integer vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public Integer getVaccineDosesRequired() {
        return vaccineDosesRequired;
    }

    public void setVaccineDosesRequired(Integer vaccineDosesRequired) {
        this.vaccineDosesRequired = vaccineDosesRequired;
    }

    public LocalDate getDose1Date() {
        return dose1Date;
    }

    public void setDose1Date(LocalDate dose1Date) {
        this.dose1Date = dose1Date;
    }

    public LocalDate getDose2Date() {
        return dose2Date;
    }

    public void setDose2Date(LocalDate dose2Date) {
        this.dose2Date = dose2Date;
    }
}
