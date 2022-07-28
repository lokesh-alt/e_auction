package com.iihtproject.productservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.iihtproject.productservice.validation.impl.EnumValueValidatorImpl;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumValueValidatorImpl.class)
public @interface EnumValueValidator {
    Class<? extends Enum<?>> enumClass();
    String message() default "must be any of the following products {enumClass}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
