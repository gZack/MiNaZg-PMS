package com.minazg.service;

import com.minazg.model.Report;

import java.util.List;

public interface ReportService {
    Report findOne(Long id);
    List<Report> findAll();
    Report save(Report report);
    public Report addReportForTask(Report report);
}
