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

	@NotBlank
	private UserRoles role;

	protected Users() {
	}

	public Users(@NotBlank @Size(max = 50) String username, @NotBlank @Size(max = 50) String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
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

}
