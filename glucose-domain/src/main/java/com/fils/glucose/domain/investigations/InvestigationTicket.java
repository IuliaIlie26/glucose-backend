package com.fils.glucose.domain.investigations;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "investigation_ticket")
public class InvestigationTicket {

	@Id
	private String id;
	private Long doctorId;
	private Long patientId;
	private List<String> investigations;
	private LocalDate date;

	public String getId() {
		return id;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public List<String> getInvestigations() {
		return investigations;
	}

	public void setInvestigations(List<String> investigations) {
		this.investigations = investigations;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

}
