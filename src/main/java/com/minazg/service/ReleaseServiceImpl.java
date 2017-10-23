package com.minazg.service;


import com.minazg.model.Release;
import com.minazg.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ReleaseServiceImpl implements ReleaseService {

    @Autowired
    ReleaseRepository releaseRepository;

    @Override
    public Release findByVersionNumber(String versionNumber) {
        return releaseRepository.findByVersionNumber(versionNumber);
    }

    @Override
    public List<Release> findByVersionNumberAndProjectId(String versionNumber, Long projectId) {
        return releaseRepository.findByVersionNumberAndProjectId(versionNumber, projectId);
    }

    @Override
    public List<Release> findReleaseByProjectId(Long projectId) {
        return releaseRepository.findReleaseByProjectId(projectId);
    }

    @Override
    public void save(Release release) {
        releaseRepository.save(release);
    }

    @Override
    public List<Release> findAll() {
        return (List<Release>) releaseRepository.findAll();
    }

    @Override
    public Release findOne(Long releaseId) {
        return releaseRepository.findOne(releaseId);
    }


}
