package com.minazg.service;

import com.minazg.model.Release;
import com.minazg.model.StatusType;
import com.sun.org.apache.regexp.internal.RE;

import java.util.List;

public interface ReleaseService {

    Release findByVersionNumber(String versionNumber);

    List<Release> findReleaseByProjectId(Long projectId);

    void save(Release release);

    List<Release> findAll();

}
