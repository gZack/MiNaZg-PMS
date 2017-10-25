package com.minazg.service;

import com.minazg.model.Sprint;

import java.util.List;

public interface SprintService {

    Sprint findOne(Long id);

    List<Sprint> findSprintByTitle(String title);

    void saveSprint(Sprint sprint);

    void updateSprint(Sprint sprint);

    void deleteSprintById(Long id);

    List<Sprint> findAll();

    List<Sprint> findSprintByReleaseId(Long id);

    List<Sprint> findByTitle(String title);

}
