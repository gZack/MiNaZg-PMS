package com.minazg.controller;

import com.minazg.model.Report;
import com.minazg.model.WorkOrder;
import com.minazg.service.ReportService;
import com.minazg.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/dev")
public class DeveloperController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ReportService reportService;

    @ModelAttribute("tasks")
    public List<WorkOrder> collectMyTasks(){
        return taskService.getMyTasks();
    }

    @GetMapping(value = {"","/list"})
    public String tasks(){
        return "dev/tasks";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long userId, Model model){
        model.addAttribute("task",taskService.findOne(userId));
        return "dev/detail";
    }

    @GetMapping("/status")
    public String changeStatus(@RequestParam("status") String status,
                               @RequestParam("id") Long id, Model model){

        //check if status is the right type

        WorkOrder workOrder = taskService.findOne(id);

        workOrder.setStatus(status);

        taskService.save(workOrder);

        return "redirect:/dev/detail?id=" + id;

    }

    @GetMapping("/report")
    public String logReport(@RequestParam("taskId") Long taskId,
                            Model model){

        WorkOrder workOrder = new WorkOrder();
        workOrder.setId(taskId);

        Report report = new Report();
        report.setWorkOrder(workOrder);

        model.addAttribute("report", report);
        model.addAttribute("taskId", taskId);

        return "report/add";

    }

    @PostMapping("/report")
    public String logReport(@Valid @ModelAttribute("report") Report report,
                            BindingResult result, Model model){

        if(result.hasErrors()){
            return "report/add";
        }

        reportService.addReportForTask(report);

        return "redirect:/dev/detail?id=" + report.getWorkOrder().getId();

    }
}
