package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDto {

	@JsonProperty
	public String addressLine1;
	@JsonProperty
	public String addressLine2;
	@JsonProperty
	public String city;
	@JsonProperty
	public String country;
	@JsonProperty
	public String zipCode;
	@JsonProperty
	public String region;
}
