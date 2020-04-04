package com.fils.glucose.domain.personal.information.patient;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fils.glucose.domain.personal.information.doctor.Doctor;

public class Patient {

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
	@Email
	private String email;

	@NotBlank
	@Size(min = 10, max = 10)
	@Pattern(regexp = "[0-9]")
	private String phoneNumber;

	@NotNull
	private LocalDate birthdate;

	@NotNull
	private Address address;

	private Set<Doctor> doctors = new HashSet<>();

	protected Patient() {
	}

	public Patient(@NotBlank String firstName, @NotBlank String lastName, @NotNull Address address,
			@NotBlank String eMail, @NotBlank String phoneNumber, @NotNull LocalDate birthdate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = eMail;
		this.phoneNumber = phoneNumber;
		this.birthdate = birthdate;
	}

	public String getId() {
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

	public Set<Doctor> getDoctors() {
		return doctors;
	}
}
