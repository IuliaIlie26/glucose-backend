package com.fils.glucose.domain.patient.address;


import static java.util.Collections.singletonList;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fils.glucose.domain.ddd.BaseValueObject;
import com.fils.glucose.domain.ddd.DDD;

@DDD.ValueObject
public class Country extends BaseValueObject<Country> {
	@Constraint
	private final String value;

	public Country(String value) {
		super(Country.class);
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
	@Size(max = 20)
	public @interface Constraint {
		String message() default "Country has max 20 chars";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};
	}

	private Country() {
		super(Country.class);
		this.value = null;
	}
}
