package com.minazg.service;

import com.minazg.model.Report;
import java.util.List;

public interface ReportService {

    List<Report> findById(Integer id);

    List<Report> findAll();

    Report save(Report report);

    public Report addReportForTask(Report report);

    List<Report> findByWorkOrderId(Long workOrderId);

    List<Report> findReportsByWorkOrder_Status(String status);

    List<Report> findReportsByWorkOrder_Developer_LastName(String lastName);

    List<Report> findReportsByWorkOrder_Title(String title);
}
