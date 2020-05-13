package com.fils.glucose.domain.consultations;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "consultations")
public class Consultation {

	@Id
	private Long id;
	
	private Long doctorId;

	private Long patientId;

	private LocalTime start;

	private LocalDate day;

	public Consultation(Long doctorId, Long patientId, LocalTime start, LocalDate day) {
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.start = start;
		this.day = day;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}
}
