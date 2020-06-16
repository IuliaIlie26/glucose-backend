package com.fils.glucose.domain.personal.information.patient;

import java.util.Optional;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Address {

	private Long addressId;

	@NotBlank
	@Size(max = 50)
	private final String addressLine1;

	@Size(max = 50)
	private final String addressLine2;

	@Size(min = 6, max = 6)
	@Digits(integer = 6, fraction = 0)
	private final String zipCode;

	@NotBlank
	@Size(max = 20)
	@Pattern(regexp = "(\\p{IsAlphabetic}|'|\\s)+")
	private final String city;

	@NotBlank
	@Size(max = 20)
	@Pattern(regexp = "(\\p{IsAlphabetic}|'|\\s)+")
	private final String region;

	@NotBlank
	@Size(max = 20)
	@Pattern(regexp = "(\\p{IsAlphabetic}|'|\\s)+")
	private final String country;

	protected Address() {
		this.addressLine1 = "";
		this.region = "";
		this.city = "";
		this.country = "";
		this.zipCode = "";
		this.addressLine2 = "";
	}

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
		return Optional.ofNullable(addressLine2);
	}

	public Optional<String> getZipCode() {
		return Optional.ofNullable(zipCode);
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
