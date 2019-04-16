package com.agh.soa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Basic
    private String name;

    @Basic
    private String surname;

    @Basic
    private String nationality;

    @OneToMany(mappedBy = "author")
    private List<Book> works;


    public void addWork(Book book) {
        if(works == null){
            works = new ArrayList<>();
        }
        works.add(book);
    }
}
