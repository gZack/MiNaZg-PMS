package com.minazg.repository;

import com.minazg.model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    //@Query("Select p from Project c where c.name = :name")
    @Query("select p from Project p where p.name LIKE  %?1% ")
    public List<Project> findByName(@Param("name") String name);
}
