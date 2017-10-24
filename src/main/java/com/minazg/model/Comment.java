package com.minazg.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Comment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min = 2, message="{comment.statment.size}")
    @Column(nullable=false)
    private String statement;

    @NotEmpty
    private String componentType;

    @NotNull
    private Long componentId;

    //@NotNull
    @Column(nullable=false)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCommented;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
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

//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + id.hashCode();
//        result = 31 * result + componentType.hashCode();
//        result = 31 * result + proposer.getId().hashCode();
//        return result;
//    }
}
