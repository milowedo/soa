package com.agh.soa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="loans")
public class Loan {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id == loan.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

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

    public Loan(Date loanDate, Book book, Reader reader) {
        this.loanDate = loanDate;
        this.book = book;
        this.reader = reader;
    }
}
