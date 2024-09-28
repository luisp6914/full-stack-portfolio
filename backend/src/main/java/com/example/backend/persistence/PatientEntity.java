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

    @Column(name = "dose1_date")
    private LocalDate dose1Date;

    @Column(name = "dose2_date")
    private LocalDate dose2Date;


    //Constructors
    public PatientEntity(){
    }

    public PatientEntity(String firstName, String lastName, Integer vaccineId){
        LocalDate currentDate = LocalDate.now();
        this.firstName = firstName;
        this.lastName = lastName;
        this.vaccineId = vaccineId;
        this.dose1Date = currentDate;
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

    public LocalDate getDose1Date() {
        return dose1Date;
    }

    public LocalDate getDose2Date() {
        return dose2Date;
    }


    //Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setVaccineId(Integer vaccineId) {
        this.vaccineId = vaccineId;
    }

    public void setDose1Date(LocalDate dose1Date) {
        this.dose1Date = dose1Date;
    }

    public void setDose2Date(LocalDate dose2Date) {
        this.dose2Date = dose2Date;
    }

}