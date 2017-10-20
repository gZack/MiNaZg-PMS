package com.minazg.repository;


import com.minazg.model.Sprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long> {

}
