package com.minazg.controller;

import com.minazg.model.Sprint;
import com.minazg.model.User;
import com.minazg.model.UserRoleType;
import com.minazg.model.WorkOrder;
import com.minazg.service.SprintService;
import com.minazg.service.TaskService;
import com.minazg.service.UserService;
import com.minazg.util.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private SprintService sprintService;

    @Autowired
    private UserService userService;

    @Autowired
    private HelperUtils helperUtils;

    @ModelAttribute("developers")
    public List<User> collectDevelopers(Model model){

        model.addAttribute("sprints", sprintService.findAll());
        model.addAttribute("workOrderTypes", helperUtils.getWorkOrderTypes());

        return userService.findUsersByRoleName(UserRoleType.DEVELOPER.getUserRoleType());
    }

    @GetMapping(value = {"","/list"})
    public String tasks(Model model){

        return "task/tasks";

    }

    @GetMapping("/add")
    public String addTask(@ModelAttribute("task") WorkOrder workOrder, Model model){

        Sprint sprint = (Sprint)model.asMap().get("sprint");

        if(null == sprint && model.asMap().get("sprints") == null){
            // we shouldn't do the population for each call
            // should be put in session and each created
            // sprint should be added to that list in session
            model.addAttribute("sprints", sprintService.findAll());
        }



        return "task/add";
    }

    @PostMapping("/add")
    public String saveTask(@Valid @ModelAttribute("task") WorkOrder workOrder,
                           BindingResult result, Model model){

        if(result.hasErrors()){
            return "task/add";
        }

        Sprint sprint = (Sprint)model.asMap().get("sprint");

        if(null == sprint && model.asMap().get("sprints") == null){
            // we shouldn't do the population for each call
            // should be put in session and each created
            // sprint should be added to that list in session
            model.addAttribute("sprints", sprintService.findAll());
        }

        model.addAttribute("workOrderTypes", helperUtils.getWorkOrderTypes());

        return "task/list";
    }
}
