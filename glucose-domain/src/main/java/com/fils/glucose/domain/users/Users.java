package com.fils.glucose.domain.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Users {

	@NotBlank
	@Size(max = 50)
	private String username;

	@NotBlank
	@Size(max = 50)
	private String password;

	@NotBlank
	private String role;

	protected Users() {
	}

	public Users(@NotBlank @Size(max = 50) String username, @NotBlank @Size(max = 50) String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}
}
