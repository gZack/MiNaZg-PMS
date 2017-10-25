package com.minazg.service;

import com.minazg.model.WorkOrder;
import com.minazg.repository.TaskRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<WorkOrder> findAll() {
        return (List<WorkOrder>) taskRepository.findAll();
    }

    @Override
    public WorkOrder save(WorkOrder workOrder) {
        return taskRepository.save(workOrder);
    }

    @Override
    public WorkOrder findOne(Long id){
        WorkOrder workOrder = taskRepository.findOne(id);
        Hibernate.initialize(workOrder.getDeveloper());
        Hibernate.initialize(workOrder.getSprint());
        return workOrder;
    }

    @Override
    public List<WorkOrder> getMyTasks() {
        return taskRepository.findByDeveloper_Id(userService.getCurrentAuthenticatedUser().getId());
    }

    @Override
    public List<WorkOrder> findBySprintId(Long sprintId){
        return taskRepository.findBySprint_Id(sprintId);
    }
}
