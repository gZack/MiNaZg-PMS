package com.minazg.service;

import com.minazg.model.Release;
import com.minazg.model.StatusType;

import java.util.List;

public interface ReleaseService {

    Release findByVersionNumber(String versionNumber);

    List<Release> findReleaseByProjectId(Long projectId);

    void saveRelease(Release release);

    void updateRelease(String versionNumber, StatusType statusType);

    void updateRelease(String versionNumber, String remark);

}
