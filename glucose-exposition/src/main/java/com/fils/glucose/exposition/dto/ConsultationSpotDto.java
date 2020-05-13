package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultationSpotDto {

	@JsonProperty
	public String speciality;
	@JsonProperty
	public String doctorName;
	@JsonProperty
	public String doctorLastName;
	@JsonProperty
	public String date;
	@JsonProperty
	public String start;
	@JsonProperty
	public String end;
	@JsonProperty
	public Long doctorId;
 }
