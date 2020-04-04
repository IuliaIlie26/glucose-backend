package com.fils.glucose.exposition.service;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.service.patient.CreatePatientService;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.exposition.dto.SavePatientDto;

@Service
public class PatientFacade {

	private final CreatePatientService createPatient;
	private final PatientMapperService patientMapperService;

	public PatientFacade(CreatePatientService createPatient, PatientMapperService patientMapperService) {
		this.createPatient = createPatient;
		this.patientMapperService = patientMapperService;
	}

	public String savePatient(SavePatientDto savePatient) {
		Patient patient = patientMapperService.mapToDomain(savePatient.patient);
		return createPatient.savePatient(patient, savePatient.doctorUsername);
	}
}
