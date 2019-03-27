package com.milosz.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Cloneable{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pageNumber == book.pageNumber &&
                title.equals(book.title) &&
                author.equals(book.author) &&
                type.equals(book.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, type, pageNumber);
    }

    @Override
    public Book clone() {
        return new Book(title,author,type,price,currency,pageNumber);
    }

    private String title;
    private String author;
    private String type;
    private Integer price;
    private String currency;
    private int pageNumber;
}
