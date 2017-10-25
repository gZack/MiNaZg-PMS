package com.minazg.model;

import com.minazg.validator.DateField;
import com.minazg.validator.DateSequence;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter


public class Sprint implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String title;

    @NotEmpty
    @Column(nullable=true)
    private String description;

//    @NotNull
    @Column(nullable=false)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
//  @Pattern(regexp = " ", message ="{com.minazg.validator.DateField.message}")
//  @DateField
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @NotNull
    @Column(nullable=false)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Temporal(TemporalType.TIMESTAMP)

    private Date endDate;

    @NotEmpty
    @Column(nullable=false)
    private String status = StatusType.CREATED.getStatusType();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sprint")
    List<WorkOrder> workOrders;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "release_id", nullable = false)
    private Release release;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint)) return false;

        Sprint sprint = (Sprint) o;

        if (!id.equals(sprint.id)) return false;
        return release.getId().equals(sprint.release.getId());
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (release != null && release.getId() != null ?
                release.getId().hashCode() : 0);
        return result;
    }

//    @Override
//    public int hashCode() {
//        int result = id.hashCode();
//        result = 31 * result + release.hashCode();
//        return result;
//    }

}
