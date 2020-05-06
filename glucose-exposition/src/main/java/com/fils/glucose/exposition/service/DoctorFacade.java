package com.fils.glucose.exposition.service;

import org.springframework.stereotype.Service;
import com.fils.glucose.application.service.doctor.CrudDoctorService;
import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.exposition.dto.DoctorDto;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorFacade {

	private final CrudDoctorService crudDoctorService;
	private final DoctorMapperService doctorMapper;

	public DoctorFacade(CrudDoctorService crudDoctorService, DoctorMapperService doctorMapperService) {
		this.crudDoctorService = requireNonNull(crudDoctorService);
		this.doctorMapper = requireNonNull(doctorMapperService);
	}

	public DoctorDto findDoctorByUsername(String username) {
		Doctor doctor = crudDoctorService.findDoctorByUsername(username);
		return doctorMapper.mapFromDomain(doctor);
	}

	public String getDoctorNameAndLastname(String username) {
		Doctor doctor = crudDoctorService.findDoctorByUsername(username);
		return doctor.getFirstName() + " " + doctor.getLastName();
	}

	public Set<DoctorDto> getDoctorsList() {
		List<Doctor> doctors = crudDoctorService.findAll();
		return doctors.stream().map(doctorMapper::mapFromDomain).collect(Collectors.toSet());
	}

	public void saveDoctor(DoctorDto dto) {
		Doctor doc = doctorMapper.mapToDomain(dto);
		crudDoctorService.save(doc);

	}

	public void updateDoctor(DoctorDto dto) {

		Doctor newDoctor = updateOldDoctor(dto);
		crudDoctorService.save(newDoctor);
	}

	private Doctor updateOldDoctor(DoctorDto dto) {
		Long doctorId = Long.parseLong(dto.id);
		Doctor doctor = crudDoctorService.findDoctorById(doctorId);
		doctor.setEmail(dto.email);
		doctor.setFirstName(dto.name);
		doctor.setLastName(dto.lastname);
		doctor.setMedicalSpeciality(dto.speciality);
		doctor.setPhoneNumber(dto.phone);
		return doctor;
	}

}
