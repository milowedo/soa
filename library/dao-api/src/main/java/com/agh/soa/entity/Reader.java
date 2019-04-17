package com.agh.soa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="readers")
public class Reader {

    @Id
    @GeneratedValue
    private int id;

    @Basic
    private String name;

    @Basic
    private String surname;
}
