package com.minazg.controller;

import com.minazg.model.Project;
import com.minazg.model.StatusType;
import com.minazg.model.User;
import com.minazg.service.ProjectService;
import com.minazg.service.UserService;
import com.minazg.util.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    HelperUtils helperUtils;
    @Autowired
    UserService userService;

//    @ModelAttribute("StatusTypes")
//    public StatusType[] getStatusTypes(){
//        return helperUtils.getStatusTypes();
//    }

    @RequestMapping(value = {"", "/", "/list"})
    public String list(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "project/listProject";
    }

    @RequestMapping(value={"/add"}, method = RequestMethod.GET)
    public String addProjectForm(@ModelAttribute("newProject") Project project, Model model){
        model.addAttribute("statusTypes", helperUtils.getStatusTypes());
        model.addAttribute("projectManagerList",userService.findUsersByRoleName("PROJECT_MANAGER"));
        model.addAttribute("clientList",userService.findUsersByRoleName("CLIENT"));
        return "project/addProject";
    }

    @RequestMapping(value={"/add"}, method = RequestMethod.POST)
    public String saveProject(@Valid @ModelAttribute("newProject") Project project, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            // TODO, remove redundant code
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("projectManagerList",userService.findUsersByRoleName("PROJECT_MANAGER"));
            model.addAttribute("clientList",userService.findUsersByRoleName("CLIENT"));
            return "project/addProject";
        }
        projectService.save(project);
        redirectAttributes.addFlashAttribute("flashMessage","Project Added Successfully");
        return "redirect:/project/list";
    }

    @RequestMapping(value={"/edit/{pid}"}, method = RequestMethod.GET)
    public String editProjectForm(@PathVariable("pid") String pid, Model model){
        Project project = null;
        try{
            project = projectService.findOne(Long.valueOf(pid));
            if(project == null){
                return "project/notFound";
            }
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("projectManagerList",userService.findUsersByRoleName("PROJECT_MANAGER"));
            model.addAttribute("clientList",userService.findUsersByRoleName("CLIENT"));
            model.addAttribute("action", "edit");
        }
        catch(Exception e){
            System.out.println(e);
            return "project/notFound";
        }
        model.addAttribute("newProject", project);
        return "project/addProject";
    }

    @RequestMapping(value={"/edit/{pid}"}, method = RequestMethod.POST)
    public String editProjectForm(@Valid @ModelAttribute("newProject") Project project, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            //TODO, remove redundant code
            //project = projectService.findOne(Long.valueOf(project.getId()));
            model.addAttribute("statusTypes", helperUtils.getStatusTypes());
            model.addAttribute("projectManagerList",userService.findUsersByRoleName("PROJECT_MANAGER"));
            model.addAttribute("clientList",userService.findUsersByRoleName("CLIENT"));
            model.addAttribute("action", "edit");
            model.addAttribute("newProject", project);
            return "project/addProject";
        }

        projectService.updateProject(project);
        redirectAttributes.addFlashAttribute("flashMessage","Project Updated Successfully");
        return "redirect:/project/list";

    }

    @RequestMapping(value = "/detail/{pid}", method = RequestMethod.GET)
    public String showProject(@PathVariable String pid, Model model) {
        try{
            model.addAttribute("project",projectService.findOne(Long.valueOf(pid)));
            return "project/detail";
        }
        catch(IllegalStateException e){
            System.out.println(e);
            return "project/notFound";
        }
    }
}
