package com.fils.glucose.exposition.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import com.fils.glucose.domain.personal.information.patient.Address;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.exposition.dto.AddressDto;
import com.fils.glucose.exposition.dto.PatientDto;

@Service
public class PatientMapperService {

	public PatientDto mapFromDomain(Patient patient) {
		PatientDto patientDto = new PatientDto();
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

		patientDto.firstName = patient.getFirstName();
		patientDto.lastname = patient.getLastName();
		patientDto.birthdate = patient.getBirthdate().format(formatter);
		patientDto.eMail = patient.getEmail();
		patientDto.phone = patient.getPhoneNumber();
		patientDto.address = mapAddressDtoFromDomain(patient.getAddress());
		return patientDto;
	}

	public Patient mapToDomain(PatientDto dto) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthdate = LocalDate.parse(dto.birthdate, formatter);
		Address address = mapAddressDtoToDomain(dto.address);
		return new Patient(dto.firstName, dto.lastname, address, dto.eMail, dto.phone, birthdate);
	}

	private Address mapAddressDtoToDomain(AddressDto dto) {
		return new Address(dto.addressLine1, dto.addressLine2, dto.zipCode, dto.city, dto.region, dto.country);
	}

	private AddressDto mapAddressDtoFromDomain(Address address) {
		AddressDto addressDto = new AddressDto();
		addressDto.addressLine1 = address.getAddressLine1();
		addressDto.addressLine2 = address.getAddressLine2().orElse("");
		addressDto.city = address.getCity();
		addressDto.country = address.getCountry();
		addressDto.region = address.getRegion();
		addressDto.zipCode = address.getZipCode().orElse("");
		return addressDto;
	}
}
