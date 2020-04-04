package com.fils.glucose.domain.users;

import javax.validation.constraints.NotBlank;

public class UserRoles {

	@NotBlank
	private String userId;

	@NotBlank
	private String role;

}
