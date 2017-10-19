package com.minazg.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class WorkOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(nullable=false)
    private String title;

    @NotEmpty
    @Column(nullable=true)
    private String description;

    @NotEmpty
    @Column(nullable=false)
    private LocalDate startDate;

    @NotEmpty
    @Column(nullable=false)
    private LocalDate endDate;

    @NotEmpty
    @Column(nullable=false)
    private StatusType statusType;

    @NotEmpty
    @Column(nullable=false)
    private Set<User> developers;

    @NotEmpty
    @Column(nullable=false)
    private Report workOrderReport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sprint_id", nullable = false)
    private Sprint sprint;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public Set<User> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<User> developers) {
        this.developers = developers;
    }

    public Report getWorkOrderReport() {
        return workOrderReport;
    }

    public void setWorkOrderReport(Report workOrderReport) {
        this.workOrderReport = workOrderReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkOrder)) return false;

        WorkOrder workOrder = (WorkOrder) o;

        if (id != null ? !id.equals(workOrder.id) : workOrder.id != null) return false;
        if (title != null ? !title.equals(workOrder.title) : workOrder.title != null) return false;
        if (description != null ? !description.equals(workOrder.description) : workOrder.description != null)
            return false;
        if (startDate != null ? !startDate.equals(workOrder.startDate) : workOrder.startDate != null) return false;
        if (endDate != null ? !endDate.equals(workOrder.endDate) : workOrder.endDate != null) return false;
        if (statusType != workOrder.statusType) return false;
        if (developers != null ? !developers.equals(workOrder.developers) : workOrder.developers != null) return false;
        return workOrderReport != null ? workOrderReport.equals(workOrder.workOrderReport) : workOrder.workOrderReport == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (statusType != null ? statusType.hashCode() : 0);
        result = 31 * result + (developers != null ? developers.hashCode() : 0);
        result = 31 * result + (workOrderReport != null ? workOrderReport.hashCode() : 0);
        return result;
    }
}
