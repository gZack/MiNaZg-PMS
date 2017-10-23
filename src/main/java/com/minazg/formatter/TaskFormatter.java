package com.minazg.formatter;

import com.minazg.model.WorkOrder;
import com.minazg.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class TaskFormatter implements Formatter<WorkOrder> {

    @Autowired
    private TaskService taskService;

    @Override
    public WorkOrder parse(String text, Locale locale) throws ParseException {
        if(text != null && !text.isEmpty()){
            return taskService.findOne(Long.parseLong(text));
        }
        return null;
    }

    @Override
    public String print(WorkOrder object, Locale locale) {
        if(object != null && object.getId() != null){
            return String.valueOf(object.getId());
        }
        return null;
    }
}
