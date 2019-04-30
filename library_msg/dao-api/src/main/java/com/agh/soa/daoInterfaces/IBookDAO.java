package com.agh.soa.daoInterfaces;



import com.agh.soa.entity.Book;
import com.agh.soa.entity.Reader;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IBookDAO extends Serializable {

    List getAll();

    Optional<Book> getOne(int id);

    void saveNewBook(Book b);

    void update(int id, Book book);

    void delete(int id);

    List getBooks();

    void borrow(Book book, Reader logged);
}
