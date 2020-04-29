package com.fils.glucose.exposition.service;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fils.glucose.application.service.patient.ConsultPatientService;
import com.fils.glucose.application.service.patient.CrudPatientService;
import com.fils.glucose.domain.medical.info.risk.factors.RiskFactors;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.exposition.dto.AddressDto;
import com.fils.glucose.exposition.dto.PatientDto;
import com.fils.glucose.exposition.dto.RiskFactorsDto;

@Service
public class PatientFacade {

	private final CrudPatientService crudPatient;
	private final PatientMapperService patientMapperService;
	private final ConsultPatientService consultPatientService;

	public PatientFacade(CrudPatientService createPatient, PatientMapperService patientMapperService,
			ConsultPatientService consultPatientService) {
		this.crudPatient = requireNonNull(createPatient);
		this.patientMapperService = requireNonNull(patientMapperService);
		this.consultPatientService = requireNonNull(consultPatientService);
	}

	public Long savePatient(PatientDto patientDto) {
		Patient patient = patientMapperService.mapToDomain(patientDto);
		return crudPatient.savePatient(patient);
	}

	public String getFullFormatAgeById(Long id) {
		return consultPatientService.getFullFormatAgeById(id);
	}

	public void saveRiskFactors(RiskFactorsDto riskFactorsDto) {
		RiskFactors riskFactors = patientMapperService.mapRiskFactorsToDomain(riskFactorsDto);
		crudPatient.saveRiskFactors(riskFactors);
	}

	public Set<PatientDto> getAllPatients() {
		List<Patient> allPatients = consultPatientService.getAllPatients();
		Set<PatientDto> patientsSet = allPatients.stream().map(patientMapperService::mapFromDomain)
				.collect(Collectors.toSet());
		patientsSet.forEach(this::computeFullAddress);
		return patientsSet;
	}

	private void computeFullAddress(PatientDto patient) {
		AddressDto address = patient.address;
		patient.fullAddress = address.addressLine1 + ", ";
		if (!StringUtils.isEmpty(address.addressLine2)) {
			patient.fullAddress += address.addressLine2 + ", ";
		}
		patient.fullAddress += address.zipCode + " " + address.city + ", " + address.region + ", " + address.country;
	}

	public void deletePatientById(String id) {
		Long idLongValue = Long.parseLong(id);
		crudPatient.deletePatientById(idLongValue);

	}

	public PatientDto getPatientById(Long id) {
		Patient patient = consultPatientService.getPatientById(id);
		return patientMapperService.mapFromDomain(patient);
	}

	public void updatePatient(PatientDto dto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthdate = LocalDate.parse(dto.birthdate, formatter);

		Long idLongValue = Long.parseLong(dto.id);
		Patient oldPatient = consultPatientService.getPatientById(idLongValue);
		oldPatient.setAddress(patientMapperService.mapAddressDtoToDomain(dto.address));
		oldPatient.setBirthdate(birthdate);
		oldPatient.setCnp(dto.cnp);
		oldPatient.setFirstName(dto.name);
		oldPatient.setLastName(dto.lastname);
		oldPatient.setEmail(dto.email);
		oldPatient.setPhoneNumber(dto.phone);
		crudPatient.updatePatient(oldPatient);
	}
}
