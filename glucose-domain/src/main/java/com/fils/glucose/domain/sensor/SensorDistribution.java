package com.fils.glucose.domain.sensor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sensor_distribution")
public class SensorDistribution {

	@Id
	private final String sensorId;
	private final Long patientId;

	public SensorDistribution() {
		this.sensorId = null;
		this.patientId = null;
	}

	public SensorDistribution(String sensorId, Long patientId) {
		this.patientId = patientId;
		this.sensorId = sensorId;
	}

	public String getSensorId() {
		return sensorId;
	}

	public Long getPatientId() {
		return patientId;
	}
}
