package com.fils.glucose.domain.patient;

import java.util.List;
import static java.util.Arrays.asList;

import com.fils.glucose.domain.contact.information.NamePart;
import com.fils.glucose.domain.ddd.BaseValueObject;
import com.fils.glucose.domain.ddd.DDD;

@DDD.ValueObject
public class PatientName extends BaseValueObject<PatientName> {
	
	private final NamePart firstName;
	private final NamePart lastName;

	public PatientName(NamePart firstName, NamePart lastName) {
		super(PatientName.class);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	private PatientName() {
		super(PatientName.class);
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
