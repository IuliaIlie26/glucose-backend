package com.fils.glucose.domain.personal.information.patient;

import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "personalInformation", name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	private String id;

	@NotBlank
	@Size(max = 50)
	@Column(name = "ADDRESS_LINE1")
	private final String addressLine1;

	@Size(max = 50)
	@Column(name = "ADDRESS_LINE2")
	private final String addressLine2;

	@Size(min = 6, max = 6)
	@Pattern(regexp = "[0-9]")
	@Column(name = "ZIPCODE")
	private final String zipCode;

	@NotBlank
	@Size(max = 20)
	@Pattern(regexp = "[a-zA-Z]")
	@Column(name = "CITY")
	private final String city;

	@NotBlank
	@Size(max = 20)
	@Pattern(regexp = "[a-zA-Z]")
	@Column(name = "REGION")
	private final String region;

	@NotBlank
	@Size(max = 20)
	@Pattern(regexp = "[a-zA-Z]")
	@Column(name = "COUNTRY")
	private final String country;

	public Address(String addressLine1, String addressLine2, String zipCode, String city, String region,
			String country) {
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.zipCode = zipCode;
		this.city = city;
		this.region = region;
		this.country = country;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public Optional<String> getAddressLine2() {
		return Optional.of(addressLine2);
	}

	public Optional<String> getZipCode() {
		return Optional.of(zipCode);
	}

	public String getCity() {
		return city;
	}

	public String getRegion() {
		return region;
	}

	public String getCountry() {
		return country;
	}

}
