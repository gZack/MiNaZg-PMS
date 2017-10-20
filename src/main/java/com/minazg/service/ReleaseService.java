package com.minazg.service;

import com.minazg.model.Release;
import com.minazg.model.StatusType;

import java.util.List;

public interface ReleaseService {

    Release findByVersionNumber(String versionNumber);

    List<Release> findReleaseByProjectId(Long projectId);


}
