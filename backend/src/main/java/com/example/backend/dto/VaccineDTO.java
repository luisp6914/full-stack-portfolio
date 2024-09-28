package com.example.backend.dto;

public class VaccineDTO {
    private String name;
    private Integer dosesRequired;
    private Integer doseInterval;
    private Integer dosesReceived;
    private Integer dosesRemaining;

    public VaccineDTO(){}

    public VaccineDTO(String name, Integer dosesRequired, Integer doseInterval, Integer dosesReceived, Integer dosesRemaining) {
        this.name = name;
        this.dosesRequired = dosesRequired;
        this.doseInterval = doseInterval;
        this.dosesReceived = dosesReceived;
        this.dosesRemaining = dosesRemaining;
    }

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

    public Integer getDoseInterval() {
        return doseInterval;
    }

    public void setDoseInterval(Integer doseInterval) {
        this.doseInterval = doseInterval;
    }

    public Integer getDosesReceived() {
        return dosesReceived;
    }

    public void setDosesReceived(Integer dosesReceived) {
        this.dosesReceived = dosesReceived;
    }

    public Integer getDosesRemaining() {
        return dosesRemaining;
    }

    public void setDosesRemaining(Integer dosesRemaining) {
        this.dosesRemaining = dosesRemaining;
    }
}
