package com.fils.glucose.exposition.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientAlertsDto {

	@JsonProperty
	public String id;
	@JsonProperty
	public Long patientId;
	@JsonProperty
	public String timestamp;
}
