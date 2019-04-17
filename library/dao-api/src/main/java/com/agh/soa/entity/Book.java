package com.agh.soa.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="books")
@Entity
public class Book implements Cloneable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Author author;

    @Basic
    private String title;

    @Column(name = "year", nullable = false)
    private Integer yearPublished;


}
