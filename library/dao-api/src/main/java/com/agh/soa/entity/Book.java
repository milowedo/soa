package com.agh.soa.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="book")
@Entity
public class Book implements Cloneable, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "surname", nullable = false)
    private String author_surname;

    @Column(name = "name", nullable = false)
    private String author_name;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "isbn", nullable = false)
    private String ISBN;

    @Column(name = "year", nullable = false)
    private Integer yearPublished;

    @Column(name = "price", nullable = false)
    private Integer price;

    public Book(String author_surname, String author_name, String title, String ISBN, Integer yearPublished, Integer price) {
        this.author_surname = author_surname;
        this.author_name = author_name;
        this.title = title;
        this.ISBN = ISBN;
        this.yearPublished = yearPublished;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author_surname='" + author_surname + '\'' +
                ", author_name='" + author_name + '\'' +
                ", title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", yearPublished=" + yearPublished +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return author_surname.equals(book.author_surname) &&
                author_name.equals(book.author_name) &&
                title.equals(book.title) &&
                ISBN.equals(book.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
