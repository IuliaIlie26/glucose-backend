package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientDto {

	@JsonProperty
	public String id;
	@JsonProperty
	public String name;
	@JsonProperty
	public String lastname;
	@JsonProperty
	public String birthdate;
	@JsonProperty
	public String email;
	@JsonProperty
	public String cnp;
	@JsonProperty
	public String phone;
	@JsonProperty
	public AddressDto address;
	@JsonProperty
	public String fullAddress;
	
	public String getId(){
		return id;
	}
}
