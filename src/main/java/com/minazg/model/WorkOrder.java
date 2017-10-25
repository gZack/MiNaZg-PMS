package com.minazg.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class WorkOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String status = StatusType.CREATED.getStatusType();

    @NotEmpty
    @Column(nullable = false)
    private String type;

    @NotEmpty
    @Column(nullable=false)
    private String title;

    @Size(min = 20,max = 200,message = "please describe the task")
    private String description;

    @NotNull
    @Column(nullable=false)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

/*
    @NotNull
    @Column(nullable=false)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
*/

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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "dev_user_id")
    private User developer;

    @OneToMany(mappedBy = "workOrder",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Report> workOrderReports;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;


}
