package com.fils.glucose.exposition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

	@JsonProperty
	public String username;
	@JsonProperty
	public String password;
	@JsonProperty
	public String role;

}
