package com.fils.glucose.exposition.service;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.fils.glucose.application.service.patient.CrudPatientService;
import com.fils.glucose.application.service.sensor.SensorService;
import com.fils.glucose.domain.medical.info.risk.factors.RiskFactors;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.exposition.dto.AddressDto;
import com.fils.glucose.exposition.dto.MessageDto;
import com.fils.glucose.exposition.dto.PatientDistributionDto;
import com.fils.glucose.exposition.dto.PatientDto;
import com.fils.glucose.exposition.dto.RiskFactorsDto;

@Service
public class PatientFacade {

	private final CrudPatientService crudPatientService;
	private final PatientMapperService patientMapperService;
	private final SensorService sensorService;

	public PatientFacade(CrudPatientService crudPatientService, PatientMapperService patientMapperService,
			SensorService sensorService) {
		this.crudPatientService = requireNonNull(crudPatientService);
		this.patientMapperService = requireNonNull(patientMapperService);
		this.sensorService = requireNonNull(sensorService);
	}

	public Long savePatient(PatientDto patientDto) {
		Patient patient = patientMapperService.mapToDomain(patientDto);
		return crudPatientService.savePatient(patient);
	}

	public String getFullFormatAgeById(Long id) {
		return crudPatientService.getFullFormatAgeById(id);
	}

	public void saveRiskFactors(RiskFactorsDto riskFactorsDto) {
		RiskFactors riskFactors = patientMapperService.mapRiskFactorsToDomain(riskFactorsDto);
		crudPatientService.saveRiskFactors(riskFactors);
	}

	public Set<PatientDto> getAllPatients() {
		List<Patient> allPatients = crudPatientService.getAllPatients();
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
		crudPatientService.deletePatientById(idLongValue);

	}

	public PatientDto getPatientById(Long id) {
		Patient patient = crudPatientService.getPatientById(id);
		return patientMapperService.mapFromDomain(patient);
	}

	public void updatePatient(PatientDto dto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthdate = LocalDate.parse(dto.birthdate, formatter);

		Long idLongValue = Long.parseLong(dto.id);
		Patient oldPatient = crudPatientService.getPatientById(idLongValue);
		oldPatient.setAddress(patientMapperService.mapAddressDtoToDomain(dto.address));
		oldPatient.setBirthdate(birthdate);
		oldPatient.setCnp(dto.cnp);
		oldPatient.setFirstName(dto.name);
		oldPatient.setLastName(dto.lastname);
		oldPatient.setEmail(dto.email);
		oldPatient.setPhoneNumber(dto.phone);
		crudPatientService.updatePatient(oldPatient);
	}

	public MessageDto assignSensor(PatientDistributionDto dto) {

		MessageDto result = new MessageDto();

		Optional<Long> potentialPatient = crudPatientService.findByCnp(dto.patientCnp);
		if (!potentialPatient.isPresent()) {
			result.message = "patient.manage.sensor.assign.not.found";
			return result;
		}

		Long patientId = potentialPatient.get();
		if (sensorService.checkIfPatientHasSensor(patientId)) {
			result.message = "patient.manage.sensor.assign.hasSensor";
			return result;
		}

		if (sensorService.checkIfSensorIsAlreadyAssigned(dto.sensorId)) {
			result.message = "patient.manage.sensor.assign.sensor.assigned";
			return result;
		}

		sensorService.assignSensorToPatient(dto.sensorId, patientId);

		return result;
	}

}
