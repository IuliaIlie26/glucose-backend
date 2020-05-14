package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultationDto {

	@JsonProperty
	public String speciality;
	@JsonProperty
	public String doctorName;
	@JsonProperty
	public String doctorLastName;
	@JsonProperty
	public String date;
	@JsonProperty
	public String startTime;
	@JsonProperty
	public Long doctorId;
	@JsonProperty
	public String patientCnp;
 }
