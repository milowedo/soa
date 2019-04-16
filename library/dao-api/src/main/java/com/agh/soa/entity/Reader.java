package com.agh.soa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Reader {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Basic
    private String name;

    @Basic
    private String surname;
}
