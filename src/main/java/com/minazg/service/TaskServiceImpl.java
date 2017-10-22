package com.minazg.service;

import com.minazg.model.WorkOrder;
import com.minazg.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<WorkOrder> findAll() {
        return (List<WorkOrder>) taskRepository.findAll();
    }

    @Override
    public WorkOrder save(WorkOrder workOrder) {
        return taskRepository.save(workOrder);
    }
}
