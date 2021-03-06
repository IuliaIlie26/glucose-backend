package com.fils.glucose.exposition.facade;

import static java.util.Objects.requireNonNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fils.glucose.application.exception.TechnicalException;
import com.fils.glucose.application.service.patient.CrudPatientService;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.exposition.dto.AddressDto;
import com.fils.glucose.exposition.dto.PatientDto;
import com.fils.glucose.exposition.mappers.PatientMapperService;

@Service
public class PatientFacade {

	private final CrudPatientService crudPatientService;
	private final PatientMapperService patientMapperService;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public PatientFacade(CrudPatientService crudPatientService, PatientMapperService patientMapperService) {
		this.crudPatientService = requireNonNull(crudPatientService);
		this.patientMapperService = requireNonNull(patientMapperService);

	}

	public Long savePatient(PatientDto patientDto) {
		Patient patient = patientMapperService.mapToDomain(patientDto);
		return crudPatientService.savePatient(patient);
	}

	public String getFullFormatAgeById(Long id) {
		return crudPatientService.getFullFormatAgeById(id);
	}

	public List<PatientDto> getAllPatients() {
		List<Patient> allPatients = crudPatientService.getAllPatients();
		List<PatientDto> patientsSet = allPatients.stream().map(patientMapperService::mapFromDomain)
				.collect(Collectors.toList());
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

	public String getPatientNameByCnp(String cnp) {
		Patient patient = crudPatientService.findByCnp(cnp);
		return patient.getLastName() + " " + patient.getFirstName();
	}

	public PatientDto getPatientByEmail(String username) {
		Patient patient = crudPatientService.getPatientByEmail(username)
				.orElseThrow(() -> new TechnicalException("backend.patient.not.found"));
		return patientMapperService.mapFromDomain(patient);
	}

}
