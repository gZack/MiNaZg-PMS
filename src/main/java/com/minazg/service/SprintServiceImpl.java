package com.minazg.service;

import com.minazg.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minazg.model.Sprint;

import java.util.List;

@Service
@Transactional
public class SprintServiceImpl implements SprintService {

    @Autowired
    private SprintRepository sprintRepository;

    public Sprint findOne(Long id) {
        return sprintRepository.findOne(id);
    }

    public void saveSprint(Sprint sprint) {
        sprintRepository.save(sprint);
    }

    public void deleteSprintById(Long id) {
        sprintRepository.delete(id);
    }


    @Override
    public List<Sprint> findByTitleAndReleaseId(String title, Long releaseId) {
        return sprintRepository.findByTitleAndReleaseId(title, releaseId);
    }

    public List<Sprint> findSprintByReleaseId(Long id) {
        return sprintRepository.findSprintByReleaseId(id);
    }

    public List<Sprint> findByTitle(String title) {
        return sprintRepository.findByTitle(title);
    }

    @Override
    public List<Sprint> findAll() {
        return (List<Sprint>) sprintRepository.findAll();
    }

    public void updateSprint(Sprint sprint) {

        sprintRepository.save(sprint);

//        Sprint entity = sprintRepository.findOne(sprint.getId());
//        if(entity!=null){
//            entity.setDescription(sprint.getDescription());
//            entity.setStartDate(sprint.getStartDate());
//            entity.setEndDate(sprint.getEndDate());
//            entity.setTitle(sprint.getTitle());
//            entity.setRelease(sprint.getRelease());
//            entity.setStatus(sprint.getStatus());
//            entity.setWorkOrders(sprint.getWorkOrders());
//
//        }

    }


}
