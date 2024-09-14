package com.example.backend.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "patients", schema = "CovidManagement")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "vaccine_id")
    private Integer vaccineId;

    @Column(name = "first_dose_date")
    private LocalDate firstDoseDate;

    @Column(name = "second_dose_date")
    private LocalDate secondDoseDate;

    @Column(name = "recieved_second_dose")
    private boolean receivedSecondDose;

    //Constructors
    public PatientEntity(){
    }

    public PatientEntity(String firstName, String lastName, Integer vaccineId){
        LocalDate currentDate = LocalDate.now();
        this.firstName = firstName;
        this.lastName = lastName;
        this.vaccineId = vaccineId;
        this.firstDoseDate = currentDate;
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getVaccineId() {
        return vaccineId;
    }

    public LocalDate getFirstDoseDate() {
        return firstDoseDate;
    }

    public LocalDate getSecondDoseDate() {
        return secondDoseDate;
    }

    public boolean isReceivedSecondDose() {
        return receivedSecondDose;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setVaccineId(Integer vaccineId) {
        this.vaccineId = vaccineId;
    }

    public void setFirstDoseDate(LocalDate firstDoseDate) {
        this.firstDoseDate = firstDoseDate;
    }

    public void setSecondDoseDate(LocalDate secondDoseDate) {
        this.secondDoseDate = secondDoseDate;
    }

    public void setReceivedSecondDose(boolean receivedSecondDose) {
        this.receivedSecondDose = receivedSecondDose;
    }
}