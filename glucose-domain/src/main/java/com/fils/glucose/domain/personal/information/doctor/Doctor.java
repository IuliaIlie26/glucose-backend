package com.fils.glucose.domain.personal.information.doctor;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fils.glucose.domain.personal.information.patient.Patient;

public class Doctor {

	private String id;

	@NotBlank
	@Pattern(regexp = "(\\p{IsAlphabetic}|'|\\s)+")
	@Size(min = 2, max = 50)
	private String firstName;

	@NotBlank
	@Pattern(regexp = "(\\p{IsAlphabetic}|'|\\s)+")
	@Size(min = 2, max = 50)
	private String lastName;

	@NotBlank
	@Pattern(regexp = "(\\p{IsAlphabetic}|'|\\s)+")
	@Size(min = 2, max = 50)
	private String medicalSpeciality;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 10, max = 10)
	@Pattern(regexp = "[0-9]")
	private String phoneNumber;

	private Set<Patient> patients = new HashSet<>();

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

	public Set<Patient> getPatients() {
		return patients;
	}

}
