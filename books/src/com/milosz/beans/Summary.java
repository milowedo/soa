package com.milosz.beans;

import com.milosz.entities.Book;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.HashSet;

@ManagedBean(name = "Summary")
@SessionScoped
public class Summary {
    private HashSet<Book> selected = new HashSet<>();

    public HashSet<Book> getSelected() {
        return selected;
    }

    public void setSelected(HashSet<Book> selected) {
        this.selected = selected;
    }

    public void add(Book book){
        this.selected.add(book);
    }

    public String getCalculateSum() {
        HashSet<Book> original = new HashSet<>(this.selected);
        return original.stream().map(BookList::convertSingleToPln)
                .map(Book::getPrice)
                .reduce(0, (a,b) ->  a+b).toString();
    }

}
