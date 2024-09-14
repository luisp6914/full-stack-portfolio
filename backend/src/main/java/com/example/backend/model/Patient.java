package com.example.backend.model;

import java.time.LocalDate;

public class Patient {
    //Data fields
    private String firstName;
    private String lastName;
    private Integer vaccineId;
    private LocalDate firstDoseDate;
    private LocalDate secondDoseDate;
    private boolean receivedSecondDose;

    //Constructors
    public Patient(){
    }

    public Patient(String firstName, String lastName, Integer vaccineId){
        LocalDate currentDate = LocalDate.now();
        this.firstName = firstName;
        this.lastName = lastName;
        this.vaccineId = vaccineId;
        this.firstDoseDate = currentDate;
        this.secondDoseDate = null;
        this.receivedSecondDose = false;
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

    public LocalDate getFirstDoseDate() {
        return firstDoseDate;
    }

    public void setFirstDoseDate(LocalDate firstDoseDate) {
        this.firstDoseDate = firstDoseDate;
    }

    public LocalDate getSecondDoseDate() {
        return secondDoseDate;
    }

    public void setSecondDoseDate(LocalDate secondDoseDate) {
        this.secondDoseDate = secondDoseDate;
    }

    public boolean isReceivedSecondDose() {
        return receivedSecondDose;
    }

    public void setReceivedSecondDose(boolean receivedSecondDose) {
        this.receivedSecondDose = receivedSecondDose;
    }
}
