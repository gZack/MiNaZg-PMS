package com.minazg.service;

import com.minazg.model.Report;
import com.minazg.model.WorkOrder;
import com.minazg.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TaskService taskService;

    @Override
    public Report findOne(Long id) {
        return reportRepository.findOne(id);
    }

    @Override
    public List<Report> findAll() {
        return (List<Report>) reportRepository.findAll();
    }

    @Override
    public Report save(Report report) {
        report.setTimeLog(new Date());
        return reportRepository.save(report);
    }

    @Override
    public Report addReportForTask(Report report){

        WorkOrder workOrder = report.getWorkOrder();
        workOrder.setTotalProgress(report.getProgressPercentage());
        workOrder.setTotalDuration(workOrder.getTotalDuration() != null ? workOrder.getTotalDuration() : 0.0
                + report.getHoursSpent());

        taskService.save(workOrder);

        return report;
    }
}
