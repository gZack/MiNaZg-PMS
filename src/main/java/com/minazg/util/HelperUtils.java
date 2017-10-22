package com.minazg.util;

import com.minazg.model.WorkOrderType;
import org.springframework.stereotype.Component;
import com.minazg.model.StatusType;

@Component
public class HelperUtils {

    public StatusType[] getStatusTypes(){
        return StatusType.values();
    }

    public WorkOrderType[] getWorkOrderTypes(){
        return WorkOrderType.values();
    }
}
