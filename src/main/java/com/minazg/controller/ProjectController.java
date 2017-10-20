package com.minazg.controller;

import com.minazg.model.Project;
import com.minazg.model.StatusType;
import com.minazg.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @RequestMapping(value = {"", "/", "/list"})
    public String list(Model model) {

        return "project/listProject";
    }

    @RequestMapping(value={"/add"}, method = RequestMethod.GET)
    public String addProjectForm(@ModelAttribute("newProject") Project project, Model model){
        StatusType[] statusTypes = StatusType.values();
        model.addAttribute("statusTypes", statusTypes);
        return "project/addProject";
    }
}
