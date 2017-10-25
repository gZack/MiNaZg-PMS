package com.minazg.service;

import com.minazg.model.Project;
import com.minazg.model.Release;
import com.minazg.model.Sprint;
import com.minazg.model.WorkOrder;
import com.minazg.repository.ProjectRepository;
import com.minazg.repository.ReleaseRepository;
import com.minazg.repository.SprintRepository;
import com.minazg.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ReleaseRepository releaseRepository;
    @Autowired
    SprintRepository sprintRepository;
    @Autowired
    TaskRepository taskRepository;

    public void save(Project project){
        projectRepository.save(project);
    }

    public List<Project> findAll(){
        return (List<Project>) projectRepository.findAll();
    }

    public Project findOne(Long id){
        return projectRepository.findOne(id);
    }

    public void updateProject(Project project){
        projectRepository.save(project);
    }

    public List<Project> findByName(String name){
        return (List<Project>) projectRepository.findByName(name);
    }

    public HashMap<String, Object> getProjectDetail(Long pid){
        HashMap<String, Object> projectDetail = new HashMap<String, Object>();

        // list & number of release
        List<Release> releases = releaseRepository.findReleaseByProjectId(pid);
        projectDetail.put("releases", releases);
        projectDetail.put("numberOfReleases", releases.size());

        // list of sprint
        List<List<Sprint>> sprints = new ArrayList<List<Sprint>>();
        //list of workorder
        List<List<WorkOrder>> tasks = new ArrayList<List<WorkOrder>>();
        releases.forEach( release -> {
            List<Sprint> sprint = sprintRepository.findSprintByReleaseId(release.getId());
            sprints.add(sprint);
            sprint.forEach(s -> {
                List<WorkOrder> task = taskRepository.findBySprint_Id(s.getId());
                tasks.add(task);
            });
        });
        /*
        for(Release release: releases){
            List<Sprint> s = sprintRepository.findSprintByReleaseId(release.getId());
//                                            .stream()
//                                            .filter( t -> t.getStatus().equals(""))
//                                            .collect(Collectors.toList());
            sprints.add(s);
        }*/
        projectDetail.put("sprints", sprints);
        projectDetail.put("tasks", tasks);

        return projectDetail;
    }
}
