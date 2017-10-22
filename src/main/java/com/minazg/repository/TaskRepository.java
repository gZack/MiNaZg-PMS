package com.minazg.repository;

import com.minazg.model.WorkOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<WorkOrder,Long> {
}
