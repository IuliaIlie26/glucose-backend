package com.fils.glucose.exposition.service;

import org.springframework.stereotype.Service;
import com.fils.glucose.application.service.doctor.ConsultDoctorService;
import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.exposition.dto.DoctorDto;
import static java.util.Objects.requireNonNull;

@Service
public class DoctorFacade {

	private final ConsultDoctorService consultDoctorService;
	private final DoctorMapperService doctorMapper;

	public DoctorFacade(ConsultDoctorService consultDoctorService, DoctorMapperService doctorMapperService) {
		this.consultDoctorService = requireNonNull(consultDoctorService);
		this.doctorMapper = requireNonNull(doctorMapperService);
	}

	public DoctorDto findDoctorByUsername(String username) {
		Doctor doctor = consultDoctorService.findDoctorByUsername(username);
		return doctorMapper.mapFromDomain(doctor);
	}

	public String getDoctorNameAndLastname(String username) {
		Doctor doctor = consultDoctorService.findDoctorByUsername(username);
		return doctor.getFirstName() + " " + doctor.getLastName();
	}

}