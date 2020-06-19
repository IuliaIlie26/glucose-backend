package com.fils.glucose.domain.alerts;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patient_alerts")
public class PatientAlerts {

	@Id
	private String id;
	private Long patientId;
	private LocalDateTime timestamp;
	private boolean displayDeleted;

	public PatientAlerts(Long patientId, LocalDateTime timestamp, boolean displayDeleted) {
		this.patientId = patientId;
		this.timestamp = timestamp;
		this.displayDeleted = displayDeleted;
	}

	public String getId() {
		return id;
	}

	public Long getPatientId() {
		return patientId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public boolean isDisplayDeleted() {
		return displayDeleted;
	}

	public void setDisplayDeleted(boolean displayDeleted) {
		this.displayDeleted = displayDeleted;
	}
}
