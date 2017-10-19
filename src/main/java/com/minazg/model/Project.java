package com.minazg.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Project implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(nullable=false)
    private String name;

    @NotEmpty
    @Column(nullable=true)
    private String description;

    @NotEmpty
    @Column(nullable=false)
    private StatusType status;

    @NotEmpty
    @Column(nullable=false)
    private LocalDate dateStart;

    @NotEmpty
    @Column(nullable=false)
    private LocalDate dateEnd;

    @NotEmpty
    @Column(nullable=false)
    private List<User> projectManagers;

    @NotEmpty
    @Column(nullable=false)
    private List<User> clients;

    @NotEmpty
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    @Column(nullable=false)
    private Set<Release> releases;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<User> getProjectManagers() {
        return projectManagers;
    }

    public void setProjectManagers(List<User> projectManagers) {
        this.projectManagers = projectManagers;
    }

    public List<User> getClients() {
        return clients;
    }

    public void setClients(List<User> clients) {
        this.clients = clients;
    }

    public Set<Release> getReleases() {
        return releases;
    }

    public void setReleases(Set<Release> releases) {
        this.releases = releases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (description != null ? !description.equals(project.description) : project.description != null) return false;
        if (status != project.status) return false;
        if (dateStart != null ? !dateStart.equals(project.dateStart) : project.dateStart != null) return false;
        if (dateEnd != null ? !dateEnd.equals(project.dateEnd) : project.dateEnd != null) return false;
        if (projectManagers != null ? !projectManagers.equals(project.projectManagers) : project.projectManagers != null)
            return false;
        if (clients != null ? !clients.equals(project.clients) : project.clients != null) return false;
        return releases != null ? releases.equals(project.releases) : project.releases == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (projectManagers != null ? projectManagers.hashCode() : 0);
        result = 31 * result + (clients != null ? clients.hashCode() : 0);
        result = 31 * result + (releases != null ? releases.hashCode() : 0);
        return result;
    }
}
