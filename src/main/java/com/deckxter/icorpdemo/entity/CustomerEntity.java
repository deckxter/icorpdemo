package com.deckxter.icorpdemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name  = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "years" )
    private Integer years;

    @Column(name = "birthday")
    private Date birthday;

}
