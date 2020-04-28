package com.fils.glucose.domain.users;

import javax.validation.constraints.NotBlank;

public class UserRoles {

	@NotBlank
	private Long id;

	@NotBlank
	private String userRole;

	public String getUserRole() {
		return userRole;
	}
}
