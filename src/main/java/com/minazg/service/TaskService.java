package com.minazg.service;

import com.minazg.model.WorkOrder;

import java.util.List;

public interface TaskService {

    List<WorkOrder> findAll();

    WorkOrder save(WorkOrder workOrder);

}
