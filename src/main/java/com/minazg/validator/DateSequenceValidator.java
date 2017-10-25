package com.minazg.validator;

import com.minazg.model.Sprint;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component

public class DateSequenceValidator implements ConstraintValidator<DateSequence, Sprint>{
    @Override
    public void initialize(DateSequence dateField) {

    }

    @Override
    public boolean isValid(Sprint sprint, ConstraintValidatorContext constraintValidatorContext) {

        if((sprint.getStartDate().compareTo(sprint.getEndDate())<0)){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("{com.minazg.validator.dateSequence.message}");

            return true;
        }

        return  false;
    }

}
