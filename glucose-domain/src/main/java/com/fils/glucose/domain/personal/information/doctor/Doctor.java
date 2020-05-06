package com.fils.glucose.domain.personal.information.doctor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Doctor {

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
	@Size(min = 2, max = 50)
	private String medicalSpeciality;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 10, max = 10)
	@Digits(integer = 10, fraction = 0)
	private String phoneNumber;

	protected Doctor() {
	}

	public Doctor(@NotNull String firstName, @NotNull String lastName, @NotNull String medicalSpeciality,
			@NotNull String email, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.medicalSpeciality = medicalSpeciality;
		this.email = email;
		this.phoneNumber = phoneNumber;
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

	public String getMedicalSpeciality() {
		return medicalSpeciality;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMedicalSpeciality(String medicalSpeciality) {
		this.medicalSpeciality = medicalSpeciality;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
