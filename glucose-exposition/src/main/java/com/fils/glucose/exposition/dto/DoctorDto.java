package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorDto {

	@JsonProperty
	public String firstName;
	@JsonProperty
	public String lastname;
	@JsonProperty
	public String speciality;
	@JsonProperty
	public String eMail;
	@JsonProperty
	public String phoneNumber;
}
