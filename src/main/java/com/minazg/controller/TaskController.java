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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

        model.addAttribute("workOrderTypes", helperUtils.getWorkOrderTypes());

        return userService.findUsersByRoleName(UserRoleType.DEVELOPER.getUserRoleType());
    }

    @GetMapping(value = {"","/list"})
    public String tasks(@RequestParam(value = "sprintId",required = false) Long sprintId,
                        Model model, @PageableDefault(size = 10) Pageable pageable){

        List<WorkOrder> tasks = null;
        int size = 0;
        if(sprintId == null){
            //model.addAttribute("sprints", sprintService.findAll());
            tasks = taskService.findAll(pageable);
            size = taskService.totalRecord();
            model.addAttribute("sprintId",sprintId);
            model.addAttribute("tasks",tasks);
        } else {
            tasks = taskService.findBySprintIdPageable(sprintId,pageable);
            size = taskService.totalRecordBySprintId(sprintId);
            model.addAttribute("tasks", tasks);
        }

        int pages = (size/10) + (size % 10 > 0 ? 1 : 0);
        model.addAttribute("totalRecords",size);
        model.addAttribute("pages", pages);

        model.addAttribute("prevPage",pageable.getPageNumber());
        model.addAttribute("nextPage",pageable.getPageNumber() + 1);
        model.addAttribute("pageSize", pageable.getPageSize());

        return "task/tasks";
    }

    @GetMapping("/add")
    public String addTask(@RequestParam(value = "sprintId",required = false) Long sprintId, Model model){

        WorkOrder workOrder = new WorkOrder();
        model.addAttribute("task",workOrder);

        if(sprintId != null){

            Sprint sprint = sprintService.findOne(sprintId);
            workOrder.setSprint(sprint);

            model.addAttribute("sprintId",sprintId);


        } else {

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

        taskService.save(workOrder);

        return "redirect:/task/list";
    }

    @GetMapping("/edit")
    public String editTask(@RequestParam("taskId") Long id,
                           @RequestParam(value = "sprintId", required = false) Long sprintId, Model model){

        WorkOrder workOrder = taskService.findOne(id);

        model.addAttribute("task",workOrder);
        model.addAttribute("projectName", workOrder.getSprint().getRelease().getProject().getName());
        model.addAttribute("releaseVersion", workOrder.getSprint().getRelease().getVersionNumber());

        return "task/edit";

    }

    @PostMapping("/edit")
    public String saveEditTask(@Valid @ModelAttribute("task") WorkOrder workOrder,
                               BindingResult bindingResult, @RequestParam("taskId") Long taskId, Model model){

        if(bindingResult.hasErrors()){
            return "task/edit";
        }

        taskService.save(workOrder);

        return "redirect:/task/list";
    }


}
