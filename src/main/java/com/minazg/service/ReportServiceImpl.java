package com.minazg.service;

import com.minazg.model.Report;
import com.minazg.model.WorkOrder;
import com.minazg.repository.ReportRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TaskService taskService;

    @Override
    public List<Report> findById(Integer id) {
        return reportRepository.findById(id);
    }


    @Override
    public Report save(Report report) {
        report.setTimeLog(new Date());
        return reportRepository.save(report);
    }

    @Override
    public Report addReportForTask(Report report){

        report.setTimeLog(new Date());

        WorkOrder workOrder = report.getWorkOrder();
        workOrder.setTotalProgress(report.getProgressPercentage());
        workOrder.setTotalDuration((workOrder.getTotalDuration() != null ? workOrder.getTotalDuration() : 0.0)
                + report.getHoursSpent());

        report.setWorkOrder(workOrder);

        workOrder.getWorkOrderReports().add(report);

        Hibernate.initialize(workOrder.getDeveloper());

        //save(report);

        taskService.save(workOrder);

        return report;
    }
    @Override
    public List<Report> findAll() {

        List<Report> reports = (List<Report>) reportRepository.findAll();
        for (Report report:
             reports) {
            Hibernate.initialize(report.getWorkOrder());
            Hibernate.initialize(report.getWorkOrder().getDeveloper());

        }

        return reports;
    }

    @Override
    public List<Report> findByWorkOrderId(Long workOrderId) {
        return reportRepository.findByWorkOrderId(workOrderId);
    }

    @Override
    public List<Report> findReportsByWorkOrder_Status(String status) {

        List<Report> reports = reportRepository.findReportsByWorkOrder_Status(status);

        for (Report report:
             reports) {
            Hibernate.initialize(report.getWorkOrder());
            Hibernate.initialize(report.getWorkOrder().getDeveloper());
        }

        return reports;
    }

    @Override
    public List<Report> findReportsByWorkOrder_Developer_LastName(String lastName){

        List<Report> reports = reportRepository.findReportsByWorkOrder_Developer_LastName(lastName);

        for (Report report:
                reports) {
            Hibernate.initialize(report.getWorkOrder());
            Hibernate.initialize(report.getWorkOrder().getDeveloper());
        }

        return reports;
    }

    @Override
    public List<Report> findReportsByWorkOrder_Title(String title){

        List<Report> reports = reportRepository.findReportsByWorkOrder_Title(title);

        for (Report report:
                reports) {
            Hibernate.initialize(report.getWorkOrder());
            Hibernate.initialize(report.getWorkOrder().getDeveloper());
        }

        return reports;
    }

//    @Override
//    public List<WorkOrder> findAll() {
//
//        List<WorkOrder> workOrders = (List<WorkOrder>)reportRepository.findAll();
//
//        for (WorkOrder workOrder : workOrders){
//            Hibernate.initialize(workOrder.getWorkOrderReports());
//        }
//
//        return workOrders;
//    }
}
