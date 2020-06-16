package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PregnancyInfoDto {

	@JsonProperty
	public Long patientId;
	@JsonProperty
	public String dueDate;
}
