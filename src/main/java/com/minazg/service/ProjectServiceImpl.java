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
import java.util.Comparator;
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

    //public HashMap<String, Object> getProjectDetail(Long pid){
        //HashMap<String, Object> projectDetail = new HashMap<String, Object>();
    public List<Release> getProjectDetail(Long pid){
        // list & number of release
        List<Release> releases = releaseRepository.findReleaseByProjectId(pid);

        // TODO, Optimize! getting data thru joined tables results in lazy initiailization error
        releases.forEach( release -> {
            // load sprints
            release.setSprints(sprintRepository.findSprintByReleaseId(release.getId()));
            // load task from each sprint
            release.getSprints().forEach(
                    sprint -> sprint.setWorkOrders(taskRepository.findBySprint_Id(sprint.getId()))
            );
        });
        // sort in reverse order
        releases.sort(
                (a,b) -> b.getVersionNumber().compareTo(a.getVersionNumber())
        );

//        projectDetail.put("releases", releases);
//        projectDetail.put("numberOfReleases", releases.size());

        return releases;
    }
}
