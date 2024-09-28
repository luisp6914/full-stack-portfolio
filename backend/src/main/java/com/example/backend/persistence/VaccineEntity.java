package com.example.backend.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "vaccines", schema = "CovidManagement")
public class VaccineEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@Column(name = "doses_required" )
	private Integer dosesRequired;

	@Column(name = "dose_intervals")
	private Integer doseIntervals;

	@Column(name = "doses_received")
	private Integer dosesReceived;

	@Column(name = "doses_remaining")
	private Integer dosesRemaining;

	public VaccineEntity(String name, Integer dosesRequired, Integer doseIntervals, Integer dosesReceived) {
		this.name = name;
		this.dosesRequired = dosesRequired;
		this.doseIntervals = doseIntervals;
		this.dosesReceived = dosesReceived;
		this.dosesRemaining = dosesReceived;
	}

	public VaccineEntity(){
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getDosesRequired() {
		return dosesRequired;
	}

	public Integer getDoseIntervals() {
		return doseIntervals;
	}

	public Integer getDosesReceived() {
		return dosesReceived;
	}

	public Integer getDosesRemaining() {
		return dosesRemaining;
	}

	//Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setDosesRequired(Integer dosesRequired) {
		this.dosesRequired = dosesRequired;
	}

	public void setDoseIntervals(Integer daysBetweenDoses) {
		this.doseIntervals = daysBetweenDoses;
	}

	public void setDosesReceived(Integer dosesReceived) {
		this.dosesReceived = dosesReceived;
	}

	public void setDosesRemaining(Integer dosesRemaining) {
		this.dosesRemaining = dosesRemaining;
	}
}