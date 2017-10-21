package com.minazg.service;

import com.minazg.model.Project;
import com.minazg.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

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
}
