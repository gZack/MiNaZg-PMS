package com.minazg.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Comment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(nullable=false)
    private String statement;

    @NotEmpty
    @Column(nullable=false)
    private LocalDate dateCommented;

    @NotEmpty
    @Column(nullable=false)
    private User proposer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public LocalDate getDateCommented() {
        return dateCommented;
    }

    public void setDateCommented(LocalDate dateCommented) {
        this.dateCommented = dateCommented;
    }

    public User getProposer() {
        return proposer;
    }

    public void setProposer(User proposer) {
        this.proposer = proposer;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "statement='" + statement + '\'' +
                ", dateCommented=" + dateCommented +
                ", proposer=" + proposer +
                '}';
    }
}
