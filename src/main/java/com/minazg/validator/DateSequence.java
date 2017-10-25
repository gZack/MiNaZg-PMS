package com.minazg.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@Target({TYPE, ANNOTATION_TYPE, METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = DateSequenceValidator.class)
@Documented
public @interface DateSequence {

    String message() default "{com.minazg.validator.dateSequence.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


//    int maximum();

}

