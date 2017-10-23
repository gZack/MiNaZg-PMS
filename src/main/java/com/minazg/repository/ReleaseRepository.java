package com.minazg.repository;

import com.minazg.model.Release;
import com.minazg.model.StatusType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseRepository extends CrudRepository<Release, Long> {

    public List<Release> findReleaseByProjectId(Long projectId);

    Release findByVersionNumber(String versionNumber);

    List<Release> findByVersionNumberAndProjectId(String versionNumber, Long projectId);
}
