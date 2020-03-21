package com.fils.glucose.domain.contact.information;

import static java.util.Arrays.asList;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.fils.glucose.domain.ddd.BaseValueObject;
import com.fils.glucose.domain.ddd.DDD;

@DDD.ValueObject
public class ContactInformation extends BaseValueObject<ContactInformation> {

	@NotNull
	private final Email eMail;
	@NotNull
	private final PhoneNumber phoneNumber;

	public ContactInformation(Email email, PhoneNumber phoneNumber) {
		super(ContactInformation.class);
		this.eMail = email;
		this.phoneNumber = phoneNumber;
		validate(this);
	}

	@Override
	protected List<Object> attributesToIncludeInEqualityCheck() {
		return asList(eMail, phoneNumber);
	}

}
