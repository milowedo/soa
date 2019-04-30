package com.agh.soa.daoInterfaces;

import com.agh.soa.entity.Author;
import com.agh.soa.entity.Reader;

import java.io.Serializable;
import java.util.List;

public interface IAuthorDAO extends Serializable {
    void addAuthor(Author author);
    Author getAuthorById(int id);
    List getAllAuthors();

    List getAuthorsByReader(Reader reader);
}
