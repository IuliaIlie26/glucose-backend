package com.fils.glucose.exposition.mappers;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.service.doctor.CrudDoctorService;
import com.fils.glucose.application.service.patient.CrudPatientService;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.domain.sensor.SensorDistribution;
import com.fils.glucose.exposition.dto.SensorDistributionDto;

@Service
public class SensorDistributionMapperService {

	private final CrudPatientService crudPatientService;
	private final CrudDoctorService crudDoctorService;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public SensorDistributionMapperService(CrudPatientService crudPatientService, CrudDoctorService crudDoctorService) {
		this.crudPatientService = crudPatientService;
		this.crudDoctorService = crudDoctorService;
	}

	public SensorDistributionDto mapFromDomain(SensorDistribution distribution) {
		SensorDistributionDto dto = new SensorDistributionDto();
		Patient patient = crudPatientService.getPatientById(distribution.getPatientId());
		dto.patientCnp = patient.getCnp();
		dto.patientName = patient.getFirstName() + " " + patient.getLastName();
		dto.status = distribution.getStatus().name();
		dto.deactivationDate = distribution.getDeactivationDate().map(formatter::format).orElse("");
		dto.activationDate = distribution.getActivationDate().map(formatter::format).orElse("");
		Optional<Long> doctorId = distribution.getDoctorId();
		if (doctorId.isPresent()) {
			dto.doctorName = crudDoctorService.getDoctorNameAndLastname(doctorId.get());
		} else {
			dto.doctorName = "";
		}
		dto.sensorId = distribution.getSensorId();
		return dto;
	}

}
