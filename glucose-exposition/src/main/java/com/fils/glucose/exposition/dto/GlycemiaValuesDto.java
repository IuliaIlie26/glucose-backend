package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlycemiaValuesDto {
	@JsonProperty
	public String sensorId;
	@JsonProperty
	public String value;
	@JsonProperty
	public String timestamp;

}
