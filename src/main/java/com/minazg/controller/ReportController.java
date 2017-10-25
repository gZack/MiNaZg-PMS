package com.minazg.controller;

import com.minazg.model.*;
import com.minazg.service.ReportService;
import com.minazg.service.UserService;
import com.minazg.util.HelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private HelperUtils helperUtils;

    @Autowired
    private UserService userService;


    @ModelAttribute("searchType")
    public Map<String, String> collectDevelopers(Model model) {

        Map<String, String> searchType = new LinkedHashMap<String, String>();
        searchType.put("Developer", "Developer");
        searchType.put("Task", "Task");
        searchType.put("Status", "Status");

//        return new String[]{"Developer", "Task", "Status"};
        return searchType;

    }

    @RequestMapping(value = {"", "/", "list"})
    public String showTaskReport(Model model) {

//        List<WorkOrder> workOrders = reportService.findAll();

//        List<Report> reports = (List<Report>) workOrders.get(0).getWorkOrderReports();


        model.addAttribute("reports", reportService.findAll());
        return "report/reportTask";
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String releaseDetail(Model model, @RequestParam(value = "searchType", required = false) String searchType,
                                @RequestParam(value = "name", required = false) String key) {

        List<Report> reports = null;


        if (searchType.equals("Developer")) {
            reports = reportService.findReportsByWorkOrder_Developer_LastName(key);
        } else if (searchType.equals("Task")) {
            reports = reportService.findReportsByWorkOrder_Title(key);
        } else if (searchType.equals("Status")) {
            reports = reportService.findReportsByWorkOrder_Status(key);
        }

        model.addAttribute("reports", reports);
//        model.addAttribute("projectName", projectService.findOne(Long.valueOf(projectId)).getName());
//        model.addAttribute("projectId", projectId);
//        model.addAttribute("statusTypes", helperUtils.getStatusTypes());

        return "report/reportTask";
    }
}
