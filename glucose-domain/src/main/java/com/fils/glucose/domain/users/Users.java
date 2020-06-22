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

	private UserRoles role;

	public Users() {
	}

	public Users(@NotBlank @Size(max = 50) String username, @NotBlank @Size(max = 50) String password, UserRoles role) {
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

	public UserRoles getRole() {
		return role;
	}

	
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
