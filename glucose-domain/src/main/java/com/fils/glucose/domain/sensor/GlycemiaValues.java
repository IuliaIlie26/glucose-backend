package com.fils.glucose.domain.sensor;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "glycemia_values")
public class GlycemiaValues {

	private String sensorId;
	private String value;
	private LocalDateTime timestamp;
	
	public String getSensorId() {
		return sensorId;
	}

	public String getValue() {
		return value;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

}
