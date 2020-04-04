package com.fils.glucose.domain.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Users {

	private String userId;

	@NotBlank
	@Size(max = 50)
	private String username;

	@NotBlank
	@Size(max = 50)
	private String password;

}
