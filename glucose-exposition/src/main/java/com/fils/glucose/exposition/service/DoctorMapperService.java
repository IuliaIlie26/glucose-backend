package com.fils.glucose.exposition.service;

import org.springframework.stereotype.Service;

import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.exposition.dto.DoctorDto;

@Service
public class DoctorMapperService {

	public Doctor mapToDomain(DoctorDto dto) {
		return new Doctor(dto.firstName, dto.lastname, dto.speciality, dto.eMail, dto.phoneNumber);
	}

	public DoctorDto mapFromDomain(Doctor doctor) {

		DoctorDto dto = new DoctorDto();
		dto.firstName = doctor.getFirstName();
		dto.lastname = doctor.getLastName();
		dto.eMail = doctor.getEmail();
		dto.phoneNumber = doctor.getPhoneNumber();
		dto.speciality = doctor.getMedicalSpeciality();
		return dto;
	}
}
