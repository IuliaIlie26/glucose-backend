package com.fils.glucose.domain.consultations;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "consultations")
public class Consultation {

	@Id
	private String id;

	private Long doctorId;

	private Long patientId;

	private LocalDateTime consultationDate;

	public Consultation(Long doctorId, Long patientId, LocalDateTime consultationDate) {
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.consultationDate = consultationDate;
	}
	
	public String getId() {
		return id;
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

	public LocalDateTime getConsultationDate() {
		return consultationDate;
	}

	public void setConsultationDate(LocalDateTime consultationDate) {
		this.consultationDate = consultationDate;
	}

}
