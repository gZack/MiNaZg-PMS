package com.minazg.converter;

import com.minazg.model.Sprint;
import com.minazg.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SprintConverter implements Converter<Object, Sprint> {

    @Autowired
    private SprintService sprintService;

    @Override
    public Sprint convert(Object source) {

        Sprint sprint = (Sprint)source;

        if(sprint != null && sprint.getId() != null){
            return sprintService.findOne(sprint.getId());
        }

        return null;

    }
}
