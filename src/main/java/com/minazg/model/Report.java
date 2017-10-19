package com.minazg.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Report implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(nullable=false)
    private ComponentType componentType;

    @NotEmpty
    @Column(nullable=false)
    private Integer elementId;

    @NotEmpty
    @Column(nullable=true)
    private List<Comment> comments;

    @NotEmpty
    @Column(nullable=false)
    private LocalDate timeLog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public LocalDate getTimeLog() {
        return timeLog;
    }

    public void setTimeLog(LocalDate timeLog) {
        this.timeLog = timeLog;
    }

    @Override
    public String toString() {
        return "Report{" +
                "componentType=" + componentType +
                ", elementId=" + elementId +
                ", comments=" + comments.toString() +
                ", timeLog=" + timeLog +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;

        Report report = (Report) o;

        if (id != null ? !id.equals(report.id) : report.id != null) return false;
        if (componentType != report.componentType) return false;
        if (elementId != null ? !elementId.equals(report.elementId) : report.elementId != null) return false;
        if (comments != null ? !comments.equals(report.comments) : report.comments != null) return false;
        return timeLog != null ? timeLog.equals(report.timeLog) : report.timeLog == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (componentType != null ? componentType.hashCode() : 0);
        result = 31 * result + (elementId != null ? elementId.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (timeLog != null ? timeLog.hashCode() : 0);
        return result;
    }
}
