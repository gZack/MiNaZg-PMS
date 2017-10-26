package com.minazg.service;

import com.minazg.model.WorkOrder;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    List<WorkOrder> findAll(Pageable pageable);

    WorkOrder save(WorkOrder workOrder);

    WorkOrder findOne(Long id);

    List<WorkOrder> getMyTasks();

    List<WorkOrder> findBySprintId(Long sprintId);

    List<WorkOrder> findBySprintIdPageable(Long sprintId, Pageable pageable);

    int totalRecord();

    int totalRecordBySprintId(Long sprintId);

}
