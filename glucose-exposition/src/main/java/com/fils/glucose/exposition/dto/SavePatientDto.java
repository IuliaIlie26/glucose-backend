package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SavePatientDto {

	@JsonProperty
	public PatientDto patient;
	@JsonProperty
	public String doctorUsername;
}
