package com.minazg.repository;

import com.minazg.model.Sprint;
import com.minazg.model.WorkOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<WorkOrder,Long> {

    List<WorkOrder> findByDeveloper_Id(Long userId);

    List<WorkOrder> findBySprint_Id(Long sprintId);

    List<WorkOrder> findBySprint_Id(Long sprintId, Pageable pageable);

    int countAllByIdIsNotNull();

    int countAllBySprint_Id(Long sprintId);

    List<WorkOrder> findByTitleContainingOrTypeContainingOrStatusContaining(
            String q1, String q2, String q3, Pageable pageable);

    List<WorkOrder> findBySprint_IdEqualsAndTitleContainingOrTypeContainingOrStatusContaining(
            Long sprintId,String q1, String q2, String q3, Pageable pageable);

    @Query("select w from WorkOrder w where w.sprint  = :sprint " +
            "and (w.status like concat('%',:status,'%') or w.type like concat('%',:type,'%')" +
            "or w.title like concat('%',:title,'%') )")
    List<WorkOrder> filterBySprintId(@Param("sprint") Sprint sprint,
                                     @Param("status") String status,
                                     @Param("type") String type,
                                     @Param("title") String title,
                                     Pageable pageable);
}