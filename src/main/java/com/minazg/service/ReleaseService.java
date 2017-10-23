package com.minazg.service;

import com.minazg.model.Release;

import java.util.List;

public interface ReleaseService {

    Release findByVersionNumber(String versionNumber);

    List<Release> findByVersionNumberAndProjectId(String versionNumber, Long projectId);

    List<Release> findReleaseByProjectId(Long projectId);

    void save(Release release);

    List<Release> findAll();

    Release findOne(Long releaseId);


}
