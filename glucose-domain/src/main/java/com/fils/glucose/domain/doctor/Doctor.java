package com.fils.glucose.domain.doctor;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fils.glucose.domain.contact.information.ContactInformation;
import com.fils.glucose.domain.ddd.BaseAggregateRoot;
import com.fils.glucose.domain.ddd.DDD;
import com.fils.glucose.domain.patient.Patient;

@DDD.AggregateRoot
public class Doctor extends BaseAggregateRoot<Doctor, DoctorId> {

	@NotNull
	private DoctorName doctorName;
	@NotNull
	private MedicalSpeciality medicalSpeciality;
	@NotNull
	private ContactInformation contactInformation;
	@NotNull
	private Set<Patient> patients;

	protected Doctor(DoctorId doctorId, DoctorName name, MedicalSpeciality speciality,
			ContactInformation contactInformation, Set<Patient> patients) {
		super(Doctor.class, doctorId);
		this.doctorName = name;
		this.medicalSpeciality = speciality;
		this.contactInformation = contactInformation;
		this.patients = patients;
		validate(this);
	}

	public DoctorName getDoctorName() {
		return doctorName;
	}

	public MedicalSpeciality getMedicalSpeciality() {
		return medicalSpeciality;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

}
