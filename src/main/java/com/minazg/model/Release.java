package com.minazg.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Milestone")
@Getter
@Setter
public class Release implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable=false)
    private String versionNumber;

    @NotNull
    @Column(nullable=false)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;

    private String status;

    @NotEmpty
    private String remark;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "release")
    private List<Sprint> sprints;

    @ManyToOne()
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Release)) return false;
        if (!super.equals(o)) return false;

        Release release = (Release) o;

        if (!id.equals(release.id)) return false;
        if (!versionNumber.equals(release.versionNumber)) return false;
        return project.getId().equals(release.project.getId());
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + versionNumber != null ? versionNumber.hashCode() : 0;
        result = 31 * result + (project != null ? project.getId().hashCode() : 0);
        return result;
    }

}
