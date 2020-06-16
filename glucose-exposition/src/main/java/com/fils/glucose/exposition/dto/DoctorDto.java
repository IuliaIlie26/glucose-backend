package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorDto {

	@JsonProperty
	public String id;
	@JsonProperty
	public String name;
	@JsonProperty
	public String lastname;
	@JsonProperty
	public String speciality;
	@JsonProperty
	public String email;
	@JsonProperty
	public String phone;
}
