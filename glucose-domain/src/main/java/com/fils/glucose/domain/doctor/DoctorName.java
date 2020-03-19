package com.fils.glucose.domain.doctor;

import static java.util.Arrays.asList;

import java.util.List;

import com.fils.glucose.domain.contact.information.NamePart;
import com.fils.glucose.domain.ddd.BaseValueObject;
import com.fils.glucose.domain.ddd.DDD;

@DDD.ValueObject
public class DoctorName extends BaseValueObject<DoctorName> {
	
	private final NamePart firstName;
	private final NamePart lastName;

	public DoctorName(NamePart firstName, NamePart lastName) {
		super(DoctorName.class);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	private DoctorName() {
		super(DoctorName.class);
		this.firstName = null;
		this.lastName = null;
	}

	public NamePart getFirstName() {
		return firstName;
	}

	public NamePart getLastName() {
		return lastName;
	}

	public String fullName() {
		return firstName.getValue() + " " + lastName.getValue();
	}

	@Override
	protected List<Object> attributesToIncludeInEqualityCheck() {
		return asList(firstName, lastName);
	}
}
