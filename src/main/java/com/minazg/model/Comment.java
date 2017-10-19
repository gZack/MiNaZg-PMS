package com.minazg.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Comment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable=false)
    private String statement;

    @NotEmpty
    private String componentType;

    @NotNull
    private Long componentId;

    @NotNull
    @Column(nullable=false)
    private LocalDate dateCommented;

    @ManyToOne()
    @JoinColumn(name = "proposer_user_id")
    private User proposer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        if (!super.equals(o)) return false;

        Comment comment = (Comment) o;

        if (!id.equals(comment.id)) return false;
        if (!componentType.equals(comment.componentType)) return false;
        return proposer.getId().equals(comment.proposer.getId());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + componentType.hashCode();
        result = 31 * result + proposer.getId().hashCode();
        return result;
    }
}
