package com.fils.glucose.domain.contact.information;

import java.util.List;

import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fils.glucose.domain.ddd.BaseValueObject;
import com.fils.glucose.domain.ddd.DDD;

import static java.util.Collections.singletonList;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@DDD.ValueObject
public class NamePart extends BaseValueObject<NamePart> {
    @Constraint
    private final String value;

    public NamePart(String value) {
        super(NamePart.class);
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
    @Pattern(regexp = "(\\p{IsAlphabetic}|'|\\s)+")
    @Size(min = 2, max = 50)
    public @interface Constraint {
        String message() default "Name must be composed of letters with a size between 2 and 50";

        Class<?>[] groups() default  {};
        Class<? extends Payload>[] payload() default {};
    }

    private NamePart() {
        super(NamePart.class);
        this.value = null;
    }
}