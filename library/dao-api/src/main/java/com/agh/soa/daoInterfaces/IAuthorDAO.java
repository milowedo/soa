package com.agh.soa.daoInterfaces;

import com.agh.soa.entity.Author;

import java.io.Serializable;
import java.util.List;

public interface IAuthorDAO extends Serializable {
    void addAuthor(Author author);
    Author getAuthorById(int id);
    List getAllAuthors();
}
