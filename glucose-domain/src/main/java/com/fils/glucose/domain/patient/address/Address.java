package com.fils.glucose.domain.patient.address;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import static java.util.Arrays.asList;
import com.fils.glucose.domain.ddd.BaseValueObject;
import com.fils.glucose.domain.ddd.DDD;

@DDD.ValueObject
public class Address extends BaseValueObject<Address> {

	@NotBlank
	private final AddressLine addressLine1;
	private final AddressLine addressLine2;
	@NotBlank
	private final ZipCode zipCode;
	@NotBlank
	private final City city;
	@NotBlank
	private final Region region;
	@NotBlank
	private final Country country;

	public Address(AddressLine addressLine1, AddressLine addressLine2, ZipCode zipCode, City city, Region region,
			Country country) {
		super(Address.class);
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.zipCode = zipCode;
		this.city = city;
		this.region = region;
		this.country = country;
		validate(this);
	}

	public AddressLine getAddressLine1() {
		return addressLine1;
	}

	public Optional<AddressLine> getAddressLine2() {
		return Optional.of(addressLine2);
	}

	public ZipCode getZipCode() {
		return zipCode;
	}

	public City getCity() {
		return city;
	}

	public Region getRegion() {
		return region;
	}

	public Country getCountry() {
		return country;
	}

	private Address() {
		super(Address.class);
		this.addressLine1 = null;
		this.addressLine2 = null;
		this.zipCode = null;
		this.city = null;
		this.region = null;
		this.country = null;
	}

	@Override
	protected List<Object> attributesToIncludeInEqualityCheck() {
		return asList(addressLine1, addressLine2, zipCode, country, region, city);
	}
}
