package com.minazg.validator;

import com.minazg.service.SprintService;
import com.minazg.validator.DateField;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class DateFieldValidator implements ConstraintValidator<DateField, Date> {


    @Override
    public void initialize(DateField constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date datefield, ConstraintValidatorContext ctx) {

        if(datefield == null){
            return false;
        }

        if (datefield.toString().matches("[01]\\d-[0-3]\\d-\\d{4}")) return true;


        else return false;
    }

}