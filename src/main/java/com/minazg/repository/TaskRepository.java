package com.minazg.repository;

import com.minazg.model.WorkOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<WorkOrder,Long> {

    List<WorkOrder> findByDeveloper_Id(Long userId);

    //List<WorkOrder> findBySprintId(Long sprintId);
    List<WorkOrder> findBySprint_Id(Long sprintId);
}
