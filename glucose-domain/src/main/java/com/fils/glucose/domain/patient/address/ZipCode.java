package com.fils.glucose.domain.patient.address;

import static java.util.Collections.singletonList;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fils.glucose.domain.ddd.BaseValueObject;
import com.fils.glucose.domain.ddd.DDD;

@DDD.ValueObject
public class ZipCode extends BaseValueObject<ZipCode> {
	@Constraint
	private final String value;

	public ZipCode(String value) {
		super(ZipCode.class);
		this.value = value;
		validate(this);
	}

	public String getValue() {
		return value;
	}

	@Override
	protected List<Object> attributesToIncludeInEqualityCheck() {
		return singletonList(value);
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@javax.validation.Constraint(validatedBy = {})
	@NotBlank
	@Size(min =6, max = 6)
	@Pattern(regexp ="[0-9]")
	public @interface Constraint {
		String message() default "ZipCode has exacty 6 digits";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};
	}

	private ZipCode() {
		super(ZipCode.class);
		this.value = null;
	}
}
