package com.minazg.controller;

import com.minazg.model.Project;
import com.minazg.model.StatusType;
import com.minazg.service.ProjectService;
import com.minazg.util.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @ModelAttribute("StatusTypes")
    public StatusType[] getStatusTypes(){
        return helperUtils.getStatusTypes();
    }

    @RequestMapping(value = {"", "/", "/list"})
    public String list(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "project/listProject";
    }

    @RequestMapping(value={"/add"}, method = RequestMethod.GET)
    public String addProjectForm(@ModelAttribute("newProject") Project project, Model model){
        model.addAttribute("statusTypes", helperUtils.getStatusTypes());
        return "project/addProject";
    }

    @RequestMapping(value={"/add"}, method = RequestMethod.POST)
    public String saveProject(@ModelAttribute("newProject") Project project, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "project/addProject";
        }
        projectService.save(project);
        return "redirect:/project/list";
    }
}
