package com.fils.glucose.domain.doctor;

import javax.validation.constraints.NotBlank;

import com.fils.glucose.domain.contact.information.ContactInformation;
import com.fils.glucose.domain.ddd.BaseAggregateRoot;
import com.fils.glucose.domain.ddd.DDD;

@DDD.AggregateRoot
public class Doctor extends BaseAggregateRoot<Doctor, DoctorId> {

	@NotBlank
	private DoctorName name;
	@NotBlank
	private MedicalSpeciality speciality;
	@NotBlank
	private ContactInformation contactInformation;

	protected Doctor(DoctorId doctorId, DoctorName name, MedicalSpeciality speciality,
			ContactInformation contactInformation) {
		super(Doctor.class, doctorId);
		this.name = name;
		this.speciality = speciality;
		this.contactInformation = contactInformation;
		validate(this);
	}

	public DoctorName getName() {
		return name;
	}

	public MedicalSpeciality getSpeciality() {
		return speciality;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

}
