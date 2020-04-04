package com.fils.glucose.domain.personal.information.doctor;

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
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fils.glucose.domain.personal.information.patient.Patient;

@Entity
@Table(schema="personalInformation", name = "doctor")
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "doctor_id")
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
	@Pattern(regexp = "(\\p{IsAlphabetic}|'|\\s)+")
	@Size(min = 2, max = 50)
	@Column(name = "SPECIALITY")
	private String medicalSpeciality;
	
	
	@NotBlank
	@Email
	@Column(name = "EMAIL")
	private String email;

	@NotBlank
	@Size(min = 10, max = 10)
	@Pattern(regexp = "[0-9]")
	@Column(name = "PHONE")
	private String phoneNumber;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "patient_doctor", joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id"), inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "patient_id"))
	private Set<Patient> patients = new HashSet<>();

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
