package com.fils.glucose.domain.personal.information.patient;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fils.glucose.domain.personal.information.doctor.Doctor;

@Entity
@Table(schema = "personalInformation", name = "patient")
public class Patient {

	@Id
	@Column(name = "patient_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@NotBlank
	@Pattern(regexp = "(\\p{IsAlphabetic}|'|\\s)+")
	@Size(min = 2, max = 50)
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotBlank
	@Pattern(regexp = "(\\p{IsAlphabetic}|'|\\s)+")
	@Size(min = 2, max = 50)
	@Column(name = "LAST_NAME")
	private String lastName;

	@NotBlank
	@Email
	@Column(name = "EMAIL")
	private String email;

	@NotBlank
	@Size(min = 10, max = 10)
	@Pattern(regexp = "[0-9]")
	@Column(name = "PHONE")
	private String phoneNumber;

	@NotNull
	@Column(name = "BIRTHDATE")
	private LocalDate birthdate;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "patient_doctor", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "patient_id"), inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id"))
	private Set<Doctor> doctors = new HashSet<>();

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
