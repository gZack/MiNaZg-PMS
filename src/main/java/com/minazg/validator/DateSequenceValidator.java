package com.minazg.validator;

import com.minazg.model.Sprint;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component

public class DateSequenceValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Sprint.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Sprint sprint = (Sprint)  target;

        if(sprint.getStartDate().after(sprint.getEndDate())) {
            errors.rejectValue("dateSequence", "com.minazg.DateSequenceValidator.message");
        }

    }


}
