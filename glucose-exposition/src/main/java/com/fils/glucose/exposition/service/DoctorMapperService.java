package com.fils.glucose.exposition.service;


import org.springframework.stereotype.Service;

import com.fils.glucose.domain.contact.information.ContactInformation;
import com.fils.glucose.domain.contact.information.Email;
import com.fils.glucose.domain.contact.information.NamePart;
import com.fils.glucose.domain.contact.information.PhoneNumber;
import com.fils.glucose.domain.doctor.Doctor;
import com.fils.glucose.domain.doctor.DoctorId;
import com.fils.glucose.domain.doctor.DoctorName;
import com.fils.glucose.domain.doctor.MedicalSpeciality;
import com.fils.glucose.exposition.dto.DoctorDto;

@Service
public class DoctorMapperService {

	public Doctor mapToDomain(DoctorDto dto) {

		DoctorName name = new DoctorName(new NamePart(dto.firstName), new NamePart(dto.lastname));
		MedicalSpeciality speciality = new MedicalSpeciality(dto.speciality);
		ContactInformation contactInformation = new ContactInformation(new Email(dto.eMail),
				new PhoneNumber(dto.phoneNumber));
		return new Doctor(new DoctorId(), name, speciality, contactInformation);
	}

	public DoctorDto mapFromDomain(Doctor doctor) {

		DoctorDto dto = new DoctorDto();
		dto.firstName = doctor.getDoctorName().getFirstName().getValue();
		dto.lastname = doctor.getDoctorName().getLastName().getValue();
		dto.eMail = doctor.getContactInformation().geteMail().getValue();
		dto.phoneNumber = doctor.getContactInformation().getPhoneNumber().getValue();
		dto.speciality = doctor.getMedicalSpeciality().getValue();
		return dto;
	}
}
