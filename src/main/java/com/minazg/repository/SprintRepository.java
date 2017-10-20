package com.minazg.repository;


import com.minazg.model.Sprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long> {

    public Sprint findSprintById(Long Id);
    public Sprint deleteSprintById(Long Id);

}
