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

	@Column(name = "days_between_doses")
	private Integer daysBetweenDoses;

	@Column(name = "total_doses_received")
	private Integer totalDosesReceived;

	@Column(name = "total_doses_left")
	private Integer totalDosesLeft;

	public VaccineEntity(String name, Integer dosesRequired, Integer daysBetweenDoses, Integer totalDosesReceived) {
		this.name = name;
		this.dosesRequired = dosesRequired;
		this.daysBetweenDoses = daysBetweenDoses;
		this.totalDosesReceived = totalDosesReceived;
		this.totalDosesLeft = totalDosesReceived;
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

	public Integer getDaysBetweenDoses() {
		return daysBetweenDoses;
	}

	public Integer getTotalDosesReceived() {
		return totalDosesReceived;
	}

	public Integer getTotalDosesLeft() {
		return totalDosesLeft;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDosesRequired(Integer dosesRequired) {
		this.dosesRequired = dosesRequired;
	}

	public void setDaysBetweenDoses(Integer daysBetweenDoses) {
		this.daysBetweenDoses = daysBetweenDoses;
	}

	public void setTotalDosesReceived(Integer totalDosesReceived) {
		this.totalDosesReceived = totalDosesReceived;
	}

	public void setTotalDosesLeft(Integer totalDosesLeft) {
		this.totalDosesLeft = totalDosesLeft;
	}
}