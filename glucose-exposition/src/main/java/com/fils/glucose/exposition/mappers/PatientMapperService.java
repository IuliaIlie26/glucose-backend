package com.fils.glucose.exposition.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import com.fils.glucose.domain.alerts.PatientAlerts;
import com.fils.glucose.domain.personal.information.patient.Address;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.exposition.controller.PatientAlertsDto;
import com.fils.glucose.exposition.dto.AddressDto;
import com.fils.glucose.exposition.dto.PatientDto;

@Service
public class PatientMapperService {

	public PatientDto mapFromDomain(Patient patient) {
		PatientDto patientDto = new PatientDto();
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		patientDto.id = patient.getId().toString();
		patientDto.name = patient.getFirstName();
		patientDto.lastname = patient.getLastName();
		patientDto.birthdate = patient.getBirthdate().format(formatter);
		patientDto.email = patient.getEmail();
		patientDto.phone = patient.getPhoneNumber();
		patientDto.address = mapAddressDtoFromDomain(patient.getAddress());
		patientDto.cnp = patient.getCnp();
		return patientDto;
	}

	public Patient mapToDomain(PatientDto dto) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthdate = LocalDate.parse(dto.birthdate, formatter);
		Address address = mapAddressDtoToDomain(dto.address);
		return new Patient(dto.name, dto.lastname, address, dto.email, dto.phone, birthdate, dto.cnp);
	}

	public Address mapAddressDtoToDomain(AddressDto dto) {
		return new Address(dto.addressLine1, dto.addressLine2, dto.zipCode, dto.city, dto.region, dto.country);
	}

	public AddressDto mapAddressDtoFromDomain(Address address) {
		AddressDto addressDto = new AddressDto();
		addressDto.addressLine1 = address.getAddressLine1();
		addressDto.addressLine2 = address.getAddressLine2().isPresent() ? address.getAddressLine2().get() : "";
		addressDto.city = address.getCity();
		addressDto.country = address.getCountry();
		addressDto.region = address.getRegion();
		addressDto.zipCode = address.getZipCode().isPresent() ? address.getZipCode().get() : "";
		return addressDto;
	}

	public PatientAlertsDto mapAlertsFromDomain(PatientAlerts bean) {
		PatientAlertsDto dto = new PatientAlertsDto();
		dto.id = bean.getId();
		dto.patientId = bean.getPatientId();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		dto.timestamp = bean.getTimestamp().format(formatter);
		return dto;
	}
}
