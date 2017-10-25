package com.minazg.repository;


import com.minazg.model.Sprint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long> {

    @Query ("select s from Sprint s where s.title LIKE  %?1% ")
   public List<Sprint> findSprintByTitle(@Param("title") String title);

    @Query("select s from Sprint s where s.release.id = :id")
    public List<Sprint> findSprintByReleaseId(@Param("id") Long id);

    List<Sprint> findByTitle(String title);

}
