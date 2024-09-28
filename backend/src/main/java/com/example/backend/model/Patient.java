package com.example.backend.model;

import java.time.LocalDate;

public class Patient {
    //Data fields
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer vaccineId;
    private LocalDate dose1Date;
    private LocalDate dose2Date;

    //Constructors
    public Patient(){
    }

    public Patient(Integer id, String firstName, String lastName, Integer vaccineId){
        LocalDate currentDate = LocalDate.now();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.vaccineId = vaccineId;
        this.dose1Date = currentDate;
        this.dose2Date = null;
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
