package com.fils.glucose.domain.patient;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import com.fils.glucose.domain.contact.information.ContactInformation;
import com.fils.glucose.domain.ddd.BaseAggregateRoot;
import com.fils.glucose.domain.ddd.DDD;
import com.fils.glucose.domain.patient.address.Address;

@DDD.AggregateRoot
public class Patient extends BaseAggregateRoot<Patient, PatientId> {

	@NotNull
	private PatientName patientName;
	@NotNull
	private Address address;
	@NotNull
	private ContactInformation contactInformation;
	@NotNull
	private LocalDate birthdate;

	public Patient(PatientId patientId, PatientName patientName, Address address,
			ContactInformation contactInformation, LocalDate birthdate) {
		super(Patient.class, patientId);
		this.patientName = patientName;
		this.address = address;
		this.contactInformation = contactInformation;
		this.birthdate = birthdate;
		validate(this);
	}

	public PatientName getPatientName() {
		return patientName;
	}

	public void setPatientName(PatientName patientName) {
		this.patientName = patientName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
}
