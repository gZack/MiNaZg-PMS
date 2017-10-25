package com.minazg.repository;

import com.minazg.model.Report;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends PagingAndSortingRepository<Report, Integer> {

    public List<Report> findByWorkOrderId(Long workOrderId);

    public List<Report> findById(Integer id);

    List<Report> findReportsByWorkOrder_Status(String status);

    List<Report> findReportsByWorkOrder_Developer_LastName(String lastName);

    List<Report> findReportsByWorkOrder_Title(String title);
}
