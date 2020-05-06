package com.fils.glucose.exposition.service;

import org.springframework.stereotype.Service;

import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.exposition.dto.DoctorDto;

@Service
public class DoctorMapperService {

	public Doctor mapToDomain(DoctorDto dto) {
		return new Doctor(dto.name, dto.lastname, dto.speciality, dto.email, dto.phone);
	}

	public DoctorDto mapFromDomain(Doctor doctor) {

		DoctorDto dto = new DoctorDto();
		dto.id= doctor.getId().toString();
		dto.name = doctor.getFirstName();
		dto.lastname = doctor.getLastName();
		dto.email = doctor.getEmail();
		dto.phone = doctor.getPhoneNumber();
		dto.speciality = doctor.getMedicalSpeciality();
		return dto;
	}
}
