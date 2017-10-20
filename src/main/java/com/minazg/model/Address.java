package com.minazg.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Address implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String street;

    private String zipCode;

    private String city;

    private String country;

    @OneToOne(mappedBy = "address", orphanRemoval = true)
    private User user;


}
