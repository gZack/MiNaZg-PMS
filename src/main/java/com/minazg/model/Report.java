package com.minazg.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Report implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private Double hoursSpent;

    @NotNull
    @Column(nullable = false)
    private Double progressPercentage;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id",nullable=true)
    private Comment comment;

    @Column(nullable=false)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeLog;

    @ManyToOne()
    @JoinColumn(name = "workOrder_id", nullable = false)
    private WorkOrder workOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;

        Report report = (Report) o;

        if (!id.equals(report.id)) return false;
        if (hoursSpent != null ? !hoursSpent.equals(report.hoursSpent) : report.hoursSpent != null) return false;
        if (progressPercentage != null ? !progressPercentage.equals(report.progressPercentage) : report.progressPercentage != null)
            return false;
        return timeLog != null ? timeLog.equals(report.timeLog) : report.timeLog == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (hoursSpent != null ? hoursSpent.hashCode() : 0);
        result = 31 * result + (progressPercentage != null ? progressPercentage.hashCode() : 0);
        result = 31 * result + (timeLog != null ? timeLog.hashCode() : 0);
        return result;
    }
}
