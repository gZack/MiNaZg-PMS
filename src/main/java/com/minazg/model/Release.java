package com.minazg.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Release implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(nullable=false)
    private Integer versionNumber;

    @NotEmpty
    @Column(nullable=false)
    private LocalDate releaseDate;

    @NotEmpty
    private String remark;

    @NotEmpty
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "release")
    @Column(nullable=false)
    private Set<Sprint> sprints;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(Set<Sprint> sprints) {
        this.sprints = sprints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Release)) return false;

        Release release = (Release) o;

        if (id != null ? !id.equals(release.id) : release.id != null) return false;
        if (versionNumber != null ? !versionNumber.equals(release.versionNumber) : release.versionNumber != null)
            return false;
        if (releaseDate != null ? !releaseDate.equals(release.releaseDate) : release.releaseDate != null) return false;
        if (remark != null ? !remark.equals(release.remark) : release.remark != null) return false;
        return sprints != null ? sprints.equals(release.sprints) : release.sprints == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (versionNumber != null ? versionNumber.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (sprints != null ? sprints.hashCode() : 0);
        return result;
    }
}
