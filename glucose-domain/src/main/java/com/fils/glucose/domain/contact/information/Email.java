package com.fils.glucose.domain.contact.information;

import static java.util.Collections.singletonList;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import com.fils.glucose.domain.ddd.BaseValueObject;
import com.fils.glucose.domain.ddd.DDD;

@DDD.ValueObject
public class Email extends BaseValueObject<Email> {
    @Constraint
    private final String value;

    public Email(String value) {
        super(Email.class);
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
    @javax.validation.constraints.Email
    public @interface Constraint {
        String message() default "Email not valid";

        Class<?>[] groups() default  {};
        Class<? extends Payload>[] payload() default {};
    }

    private Email() {
        super(Email.class);
        this.value = null;
    }
}