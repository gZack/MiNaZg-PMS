package com.minazg.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class WorkOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String status = StatusType.CREATED.getStatusType();

    @NotEmpty
    @Column(nullable = false)
    private String type = WorkOrderType.FEATURE.getWorkOrderType();

    @NotEmpty
    @Column(nullable=false)
    private String title;

    @NotEmpty
    private String description;

    @NotNull
    @Column(nullable=false)
    private Date startDate;

    @NotNull
    @Column(nullable=false)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resolvedDate;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedDate;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadLine;

    private Double totalDuration;

    private Double totalProgress;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dev_user_id")
    private User developer;

    @OneToMany(mappedBy = "workOrder",fetch = FetchType.LAZY)
    private Set<Report> workOrderReports;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id", nullable = false)
    private Sprint sprint;


}
