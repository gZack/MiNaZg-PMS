package com.minazg.controller;

import com.minazg.model.Project;
import com.minazg.service.ProjectService;
import com.minazg.util.HelperUtils;
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
    @Autowired
    HelperUtils helperUtils;

    @RequestMapping(value = {"", "/", "/list"})
    public String list(Model model) {
        return "project/listProject";
    }

    @RequestMapping(value={"/add"}, method = RequestMethod.GET)
    public String addProjectForm(@ModelAttribute("newProject") Project project, Model model){
        model.addAttribute("statusTypes", helperUtils.getStatusTypes());
        return "project/addProject";
    }
}
