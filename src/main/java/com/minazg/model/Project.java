package com.minazg.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Project implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable=false)
    private String name;

    @NotEmpty
    @Column(nullable=true)
    private String description;

    @Column(nullable=false)
    private String status = StatusType.CREATED.getStatusType();

    @NotNull
    private LocalDate dateStart;

    @NotNull
    private LocalDate dateEnd;

    @OneToOne
    @JoinColumn(name = "pm_user_id")
    private User projectManager;

    @OneToOne
    @JoinColumn(name = "client_user_id")
    private User client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private Set<Release> releases;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        if (!super.equals(o)) return false;

        Project project = (Project) o;

        if (!id.equals(project.id)) return false;
        return name.equals(project.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
