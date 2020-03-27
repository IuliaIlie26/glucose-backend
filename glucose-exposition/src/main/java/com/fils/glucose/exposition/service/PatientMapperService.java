package com.fils.glucose.exposition.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import com.fils.glucose.domain.contact.information.ContactInformation;
import com.fils.glucose.domain.contact.information.Email;
import com.fils.glucose.domain.contact.information.NamePart;
import com.fils.glucose.domain.contact.information.PhoneNumber;
import com.fils.glucose.domain.patient.Patient;
import com.fils.glucose.domain.patient.PatientId;
import com.fils.glucose.domain.patient.PatientName;
import com.fils.glucose.domain.patient.address.Address;
import com.fils.glucose.domain.patient.address.AddressLine;
import com.fils.glucose.domain.patient.address.City;
import com.fils.glucose.domain.patient.address.Country;
import com.fils.glucose.domain.patient.address.Region;
import com.fils.glucose.domain.patient.address.ZipCode;
import com.fils.glucose.exposition.dto.AddressDto;
import com.fils.glucose.exposition.dto.PatientDto;

@Service
public class PatientMapperService {

	public PatientDto mapFromDomain(PatientName patientName, Address address, LocalDate birthdate,
			ContactInformation contactInformation) {
		PatientDto patientDto = new PatientDto();
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

		patientDto.firstName = patientName.getFirstName().getValue();
		patientDto.lastname = patientName.getLastName().getValue();
		patientDto.birthdate = birthdate.format(formatter);
		patientDto.eMail = contactInformation.geteMail().getValue();
		patientDto.phone = contactInformation.getPhoneNumber().getValue();
		patientDto.address = mapAddressDtoFromDomain(address);

		return patientDto;
	}

	public Patient mapToDomain(PatientDto patientDto) {

		PatientName name = new PatientName(new NamePart(patientDto.firstName), new NamePart(patientDto.lastname));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate birthdate = LocalDate.parse(patientDto.birthdate, formatter);
		PatientId patientId = new PatientId();
		ContactInformation contactInformation = new ContactInformation(new Email(patientDto.eMail),
				new PhoneNumber(patientDto.phone));
		Address address = mapAddressDtoToDomain(patientDto, patientId);
		return new Patient(patientId, name, address, contactInformation, birthdate);
	}

	private Address mapAddressDtoToDomain(PatientDto patientDto, PatientId patientId) {
		AddressDto addressDto = patientDto.address;
		AddressLine addressLine1 = new AddressLine(addressDto.addressLine1);
		AddressLine addressLine2 = new AddressLine(addressDto.addressLine2);
		ZipCode zipCode = new ZipCode(addressDto.zipCode);
		City city = new City(addressDto.city);
		Region region = new Region(addressDto.region);
		Country country = new Country(addressDto.country);
		return new Address(patientId, addressLine1, addressLine2, zipCode, city, region, country);
	}

	private AddressDto mapAddressDtoFromDomain(Address address) {
		AddressDto addressDto = new AddressDto();
		addressDto.addressLine1 = address.getAddressLine1().getValue();
		addressDto.addressLine2 = address.getAddressLine2().map(AddressLine::getValue).orElse("");
		addressDto.city = address.getCity().getValue();
		addressDto.country = address.getCountry().getValue();
		addressDto.region = address.getRegion().getValue();
		addressDto.zipCode = address.getZipCode().map(ZipCode::getValue).orElse("");
		return addressDto;
	}
}
