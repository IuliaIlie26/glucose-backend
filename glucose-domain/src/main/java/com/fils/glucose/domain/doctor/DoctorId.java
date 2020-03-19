package com.fils.glucose.domain.doctor;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fils.glucose.domain.ddd.BaseValueObject;
import com.fils.glucose.domain.ddd.DDD;
import static java.util.Arrays.asList;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@DDD.ValueObjectId
public class DoctorId extends BaseValueObject<DoctorId> {
	@Constraint
	private final String value;

	public DoctorId(DoctorId other) {
		super(DoctorId.class);
		this.value = Objects.requireNonNull(other).getValue();
	}

	public DoctorId() {
		super(DoctorId.class);
		this.value = UUID.randomUUID().toString();
	}

	public String getValue() {
		return value;
	}

	@Override
	protected List<Object> attributesToIncludeInEqualityCheck() {
		return asList(value);
	}

	@Documented
	@Target({ ElementType.FIELD, ElementType.PARAMETER })
	@Retention(RetentionPolicy.RUNTIME)
	@NotEmpty
	@Size(max = 36)
	@javax.validation.Constraint(validatedBy = {})
	public @interface Constraint {
		String message() default "Doctor id should not be empty or over 36 chars";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};
	}
}
