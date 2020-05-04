package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientDistributionDto {
	
	@JsonProperty
	public String sensorId;
	@JsonProperty
	public String patientName;
	@JsonProperty
	public String doctorName;
	@JsonProperty
	public String activationDate;
	@JsonProperty
	public String deactivationDate;
	@JsonProperty
	public String patientCnp;
	@JsonProperty
	public String status;

}
