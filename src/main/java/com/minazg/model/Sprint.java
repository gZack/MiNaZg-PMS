package com.minazg.model;

import javafx.concurrent.Task;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Sprint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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
    private StatusType status;

    @NotEmpty
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sprint")
    @Column(nullable=false)
    List<WorkOrder> workOrders;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "release_id", nullable = false)
    private Release release;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public List<WorkOrder> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(List<WorkOrder> workOrders) {
        this.workOrders = workOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint)) return false;

        Sprint sprint = (Sprint) o;

        if (id != null ? !id.equals(sprint.id) : sprint.id != null) return false;
        if (description != null ? !description.equals(sprint.description) : sprint.description != null) return false;
        if (startDate != null ? !startDate.equals(sprint.startDate) : sprint.startDate != null) return false;
        if (endDate != null ? !endDate.equals(sprint.endDate) : sprint.endDate != null) return false;
        if (status != sprint.status) return false;
        return workOrders != null ? workOrders.equals(sprint.workOrders) : sprint.workOrders == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (workOrders != null ? workOrders.hashCode() : 0);
        return result;
    }
}
