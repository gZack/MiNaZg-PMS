package com.minazg.formatter;

import com.minazg.model.Sprint;
import com.minazg.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class SprintFormatter implements Formatter<Sprint>{

    @Autowired
    private SprintService sprintService;

    @Override
    public Sprint parse(String text, Locale locale) throws ParseException {
        if(text != null && !text.isEmpty()){
            return sprintService.findById(Long.parseLong(text));
        }
        return null;
    }

    @Override
    public String print(Sprint object, Locale locale) {

        if(object != null && object.getId() != null){
            return String.valueOf(object.getId());
        }

        return null;
    }
}
