package com.fils.glucose.domain.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Users {

	private Long id;

	@NotBlank
	@Size(max = 50)
	private String username;

	@NotBlank
	@Size(max = 50)
	private String password;

	public Long getId() {
		return id;
	}

}
