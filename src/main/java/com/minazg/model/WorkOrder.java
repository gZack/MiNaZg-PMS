package com.minazg.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
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
    @Column(nullable=false)
    private String title;

    @NotEmpty
    private String description;

    @NotNull
    @Column(nullable=false)
    private LocalDate startDate;

    @NotNull
    @Column(nullable=false)
    private LocalDate endDate;

    private LocalDate resolvedDate;

    private LocalDate closedDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate deadLine;

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
