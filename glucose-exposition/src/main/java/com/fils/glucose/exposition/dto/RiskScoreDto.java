package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RiskScoreDto {

	@JsonProperty
	public Double naylorScore;
	@JsonProperty
	public Double caliskanScore;
	@JsonProperty
	public Double vanLeeuwenScore;
	@JsonProperty
	public Double teedeScore;
	@JsonProperty
	public Double nandaScore;
}
