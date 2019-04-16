package com.agh.soa.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Basic
    private Date loanDate;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Book book;

    @ManyToOne
    private Reader reader;

}
