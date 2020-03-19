package com.fils.glucose.domain.contact.information;

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
public class PhoneNumber extends BaseValueObject<PhoneNumber> {
    @Constraint
    private final String value;

    public PhoneNumber(String value) {
        super(PhoneNumber.class);
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
    @Size(min = 10, max = 10)
    @Pattern(regexp = "[0-9]")
    public @interface Constraint {
        String message() default "Phone number must be 10 chars length and contain only numbers";

        Class<?>[] groups() default  {};
        Class<? extends Payload>[] payload() default {};
    }

    private PhoneNumber() {
        super(PhoneNumber.class);
        this.value = null;
    }
}