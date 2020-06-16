package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RiskFactorsDto {

	@JsonProperty
	public Long patientId;
	@JsonProperty
	public Integer height;
	@JsonProperty
	public Integer weight;
	@JsonProperty
	public String racialOrigin;
	@JsonProperty
	public String conceptionMethod;
	@JsonProperty
	public String familyHistoryOfDiabetes;
	@JsonProperty
	public boolean smoker;
	@JsonProperty
	public boolean macrosomicBaby;
	@JsonProperty
	public boolean previousGDM;
	@JsonProperty
	public boolean historyOfAdverseOutcomes;
	@JsonProperty
	public boolean multipara;
}
