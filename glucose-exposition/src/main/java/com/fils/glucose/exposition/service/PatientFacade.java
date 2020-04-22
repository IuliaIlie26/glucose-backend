package com.fils.glucose.exposition.service;

import static java.util.Objects.requireNonNull;

import org.springframework.stereotype.Service;
import com.fils.glucose.application.service.patient.ConsultPatientService;
import com.fils.glucose.application.service.patient.CreatePatientService;
import com.fils.glucose.domain.medical.info.risk.factors.RiskFactors;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.exposition.dto.RiskFactorsDto;
import com.fils.glucose.exposition.dto.SavePatientDto;

@Service
public class PatientFacade {

	private final CreatePatientService createPatient;
	private final PatientMapperService patientMapperService;
	private final ConsultPatientService consultPatientService;

	public PatientFacade(CreatePatientService createPatient, PatientMapperService patientMapperService,ConsultPatientService consultPatientService) {
		this.createPatient = requireNonNull(createPatient);
		this.patientMapperService = requireNonNull(patientMapperService);
		this.consultPatientService = requireNonNull(consultPatientService);
	}

	public Long savePatient(SavePatientDto savePatient) {
		Patient patient = patientMapperService.mapToDomain(savePatient.patient);
		return createPatient.savePatient(patient, savePatient.doctorUsername);
	}

	public String getFullFormatAgeById(Long id) {
		return consultPatientService.getFullFormatAgeById(id);
	}

	public void saveRiskFactors(RiskFactorsDto riskFactorsDto) {
		RiskFactors riskFactors = patientMapperService.mapRiskFactorsToDomain(riskFactorsDto);
		createPatient.saveRiskFactors(riskFactors);
	}
}
