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
	private Status status;
	private Long doctorId;
	private LocalDate activationDate;
	private LocalDate deactivationDate;

	public SensorDistribution(String sensorId, Long patientId, Status status) {
		super();
		this.sensorId = sensorId;
		this.patientId = patientId;
		this.status = status;
	}

	public SensorDistribution() {
	}

	public String getSensorId() {
		return sensorId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public Status getStatus() {
		return status;
	}

	public Optional<Long> getDoctorId() {
		return Optional.ofNullable(doctorId);
	}

	public Optional<LocalDate> getActivationDate() {
		return Optional.ofNullable(activationDate);
	}

	public Optional<LocalDate> getDeactivationDate() {
		return Optional.ofNullable(deactivationDate);
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public void setActivationDate(LocalDate activationDate) {
		this.activationDate = activationDate;
	}

	public void setDeactivationDate(LocalDate deactivationDate) {
		this.deactivationDate = deactivationDate;
	}

}
