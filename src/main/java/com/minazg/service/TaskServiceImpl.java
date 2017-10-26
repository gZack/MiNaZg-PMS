package com.minazg.service;

import com.minazg.exception.TaskNotFoundException;
import com.minazg.model.WorkOrder;
import com.minazg.repository.TaskRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public List<WorkOrder> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable).getContent();
    }

    @Override
    public WorkOrder save(WorkOrder workOrder) {
        return taskRepository.save(workOrder);
    }

    @Override
    public WorkOrder findOne(Long id){
        WorkOrder workOrder = taskRepository.findOne(id);
        if(workOrder == null){
            throw new TaskNotFoundException("Unable to find task with task Id: " +id);
        }
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

    @Override
    public List<WorkOrder> findBySprintIdPageable(Long sprintId, Pageable pageable){
        return taskRepository.findBySprint_Id(sprintId, pageable);
    }

    @Override
    public int totalRecord(){
        return taskRepository.countAllByIdIsNotNull();
    }

    @Override
    public int totalRecordBySprintId(Long sprintId){
        return taskRepository.countAllBySprint_Id(sprintId);
    }
}
