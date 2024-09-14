package com.example.backend.model;

public class Vaccine {
    //Data fields
    private String name;
    private Integer dosesRequired;
    private Integer daysBetweenDoses;
    private Integer totalDosesReceived;
    private Integer totalDosesLeft;

    //Constructor
    public Vaccine(String name, Integer dosesRequired, Integer daysBetweenDoses, Integer totalDosesReceived){
        this.name = name;
        this.dosesRequired = dosesRequired;
        this.daysBetweenDoses = daysBetweenDoses;
        this.totalDosesReceived = totalDosesReceived;
        this.totalDosesLeft = totalDosesReceived;
    }

    public Vaccine(){
    }

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDosesRequired() {
        return dosesRequired;
    }

    public void setDosesRequired(Integer dosesRequired) {
        this.dosesRequired = dosesRequired;
    }

    public Integer getDaysBetweenDoses() {
        return daysBetweenDoses;
    }

    public void setDaysBetweenDoses(Integer daysBetweenDoses) {
        this.daysBetweenDoses = daysBetweenDoses;
    }

    public Integer getTotalDosesReceived() {
        return totalDosesReceived;
    }

    public void setTotalDosesReceived(Integer totalDosesReceived) {
        this.totalDosesReceived = totalDosesReceived;
    }

    public Integer getTotalDosesLeft() {
        return totalDosesLeft;
    }

    public void setTotalDosesLeft() {
        this.totalDosesLeft = this.totalDosesReceived;
    }
}
