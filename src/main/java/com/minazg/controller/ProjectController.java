package com.minazg.controller;

import com.minazg.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @RequestMapping(value = {"", "/", "/list"})
    public String list(Model model) {

        return "project/listProject";
    }

    @RequestMapping("/add")
    public String addProjectForm(Model model){

        return "project/addProject";
    }
}
