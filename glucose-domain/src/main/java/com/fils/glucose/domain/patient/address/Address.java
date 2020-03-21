package com.fils.glucose.domain.patient.address;

import java.util.Optional;
import javax.validation.constraints.NotNull;
import com.fils.glucose.domain.ddd.BaseEntity;
import com.fils.glucose.domain.ddd.DDD;
import com.fils.glucose.domain.patient.PatientId;

@DDD.DomainEntity
public class Address extends BaseEntity<Address, PatientId> {

	@NotNull
	private final AddressLine addressLine1;
	private final AddressLine addressLine2;
	@NotNull
	private final ZipCode zipCode;
	@NotNull
	private final City city;
	@NotNull
	private final Region region;
	@NotNull
	private final Country country;

	public Address(PatientId id, AddressLine addressLine1, AddressLine addressLine2, ZipCode zipCode, City city, Region region,
			Country country) {
		super(Address.class, id);
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
}
