package com.fils.glucose.domain.personal.information.patient;

import java.time.LocalDate;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Patient {

	private Long id;

	@NotBlank
	@Pattern(regexp = "(\\p{IsAlphabetic}|'|\\s)+")
	@Size(min = 2, max = 50)
	private String firstName;

	@NotBlank
	@Pattern(regexp = "(\\p{IsAlphabetic}|'|\\s)+")
	@Size(min = 2, max = 50)
	private String lastName;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 10, max = 10)
	@Digits(integer = 10, fraction = 0)
	private String phoneNumber;

	@NotNull
	private LocalDate birthdate;

	@NotNull
	private Address address;

	@NotBlank
	@Size(min = 13, max = 13)
	@Digits(integer = 13, fraction = 0)
	private String cnp;

	protected Patient() {
	}

	public Patient(@NotBlank String firstName, @NotBlank String lastName, @NotNull Address address,
			@NotBlank String eMail, @NotBlank String phoneNumber, @NotNull LocalDate birthdate, String cnp) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = eMail;
		this.phoneNumber = phoneNumber;
		this.birthdate = birthdate;
		this.cnp = cnp;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Address getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public String getCnp() {
		return cnp;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	
}
