package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultationFilterDto {
	
	@JsonProperty
	public String startDate;

	@JsonProperty
	public String endDate;

	@JsonProperty
	public String speciality;
}
