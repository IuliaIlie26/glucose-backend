package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultationDto {

	@JsonProperty
	public Long doctorId;

	@JsonProperty
	public Long patientCnp;

	@JsonProperty
	public String start;

	@JsonProperty
	public String end;

	@JsonProperty
	public String day;
	
	@JsonProperty
	public String speciality;
}
