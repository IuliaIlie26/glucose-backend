package com.fils.glucose.exposition.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.service.doctor.CrudDoctorService;
import com.fils.glucose.application.service.patient.alerts.CrudPatientAlertService;
import com.fils.glucose.exposition.controller.PatientAlertsDto;
import com.fils.glucose.exposition.dto.PatientDto;
import com.fils.glucose.exposition.mappers.PatientMapperService;

@Service
public class PatientAlertsFacade {

	private final CrudPatientAlertService crudPatientAlertService;
	private final PatientMapperService patientMapperService;
	private final CrudDoctorService crudDoctorService;

	public PatientAlertsFacade(CrudPatientAlertService crudPatientAlertService,
			PatientMapperService patientMapperService, CrudDoctorService crudDoctorService) {
		this.crudPatientAlertService = crudPatientAlertService;
		this.crudDoctorService = crudDoctorService;
		this.patientMapperService = patientMapperService;
	}

	public List<PatientDto> getAllPatientAlertsForDoctor(String username) {
		Long doctorId = crudDoctorService.getDoctorIdByUsername(username);
		return crudPatientAlertService.getAllPatientAlertsForDoctor(doctorId).stream()
				.map(patientMapperService::mapFromDomain).collect(Collectors.toList());
	}

	public List<PatientAlertsDto> getAlertsListForPatient(Long patientId) {
		return crudPatientAlertService.findByPatientId(patientId).stream()
				.map(patientMapperService::mapAlertsFromDomain).collect(Collectors.toList());
	}

	public void deleteAlertsForDoctor(String username) {
		Long doctorId = crudDoctorService.getDoctorIdByUsername(username);
		crudPatientAlertService.deleteAlertsForDoctor(doctorId);
	}
}
