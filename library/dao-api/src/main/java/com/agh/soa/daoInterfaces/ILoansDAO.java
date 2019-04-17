package com.agh.soa.daoInterfaces;

import com.agh.soa.entity.Book;
import com.agh.soa.entity.Loan;

import java.io.Serializable;
import java.util.List;

public interface ILoansDAO extends Serializable {
    Loan getLoanByBookID(Book id);
    List getAll();
    void addLoan(Loan loan);
    List getLoans();
    void updateLoan(Loan loan);
}
