package com.minazg.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="Milestone")
@Data
public class Release implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable=false)
    private String versionNumber;

    @NotNull
    @Column(nullable=false)
    private LocalDate releaseDate;

    private String status;

    @NotEmpty
    private String remark;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "release")
    private Set<Sprint> sprints;

    @ManyToOne(fetch = FetchType.LAZY)
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
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + versionNumber.hashCode();
        result = 31 * result + project.getId().hashCode();
        return result;
    }
}
