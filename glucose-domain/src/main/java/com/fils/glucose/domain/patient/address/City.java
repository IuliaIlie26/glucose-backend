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
public class City extends BaseValueObject<City> {
	@Constraint
	private final String value;

	public City(String value) {
		super(City.class);
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
		String message() default "City has max 20 chars";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};
	}

	private City() {
		super(City.class);
		this.value = null;
	}
}
