package com.minazg.util;

import org.springframework.stereotype.Component;
import com.minazg.model.StatusType;

@Component
public class HelperUtils {

    public StatusType[] getStatusTypes(){
        return StatusType.values();
    }
}
