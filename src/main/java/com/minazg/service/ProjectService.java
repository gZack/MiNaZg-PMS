package com.minazg.service;

import com.minazg.model.Project;

import java.util.List;

public interface ProjectService {
    public void save(Project project);
    public List<Project> findAll();
}
