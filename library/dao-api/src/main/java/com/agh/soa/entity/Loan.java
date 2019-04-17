package com.agh.soa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="loans")
public class Loan {

    @Id
    @GeneratedValue
    private int id;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date loanDate;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @OneToOne(cascade = CascadeType.MERGE)
    private Book book;

    @OneToOne(cascade = CascadeType.MERGE)
    private Reader reader;

}
