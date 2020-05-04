package com.fils.glucose.domain.sensor;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sensor_distribution")
public class SensorDistribution {

	@Id
	private String sensorId;
	private Long patientId;
	private String status;
	private String doctorId;
	private LocalDate activationDate;
	private LocalDate deactivationDate;

	public SensorDistribution(String sensorId, Long patientId) {
		super();
		this.sensorId = sensorId;
		this.patientId = patientId;
	}

	public SensorDistribution() {
	}

	public String getSensorId() {
		return sensorId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public String getStatus() {
		return status;
	}

	public Optional<String> getDoctorId() {
		return Optional.ofNullable(doctorId);
	}

	public Optional<LocalDate> getActivationDate() {
		return Optional.ofNullable(activationDate);
	}

	public Optional<LocalDate> getDeactivationDate() {
		return Optional.ofNullable(deactivationDate);
	}
}
