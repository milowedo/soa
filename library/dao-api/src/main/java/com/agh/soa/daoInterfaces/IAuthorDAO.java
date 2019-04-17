package com.agh.soa.daoInterfaces;

import com.agh.soa.entity.Author;

import java.io.Serializable;
import java.util.Optional;

public interface IAuthorDAO extends Serializable {

    Optional<Author> getAuthorById(int id);

    void saveNewAuthor(Author b);

    void updateExistingAuthor(int id, Author book);

    void deleteAuthor(int id);
}
