package com.minazg.service;

import com.minazg.model.Sprint;

import java.util.List;

public interface SprintService {

    Sprint findById(Long id);

    void saveSprint(Sprint sprint);

    void updateSprint(Sprint sprint);

    void deleteSprintById(Long id);

    List<Sprint> findAllSprints();


}
