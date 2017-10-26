package com.minazg.repository;

import com.minazg.model.WorkOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<WorkOrder,Long> {

    List<WorkOrder> findByDeveloper_Id(Long userId);

    List<WorkOrder> findBySprint_Id(Long sprintId);

    List<WorkOrder> findBySprint_Id(Long sprintId, Pageable pageable);

    int countAllByIdIsNotNull();

    int countAllBySprint_Id(Long sprintId);
}
