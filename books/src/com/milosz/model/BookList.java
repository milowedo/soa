package com.milosz.model;

import com.milosz.entities.Book;
import com.milosz.entities.Currency;
import org.omnifaces.cdi.ViewScoped;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;

@ManagedBean(name = "BookList")
@ViewScoped
public class BookList implements Serializable {
   private Collection<Book> books = new LinkedList<>(Arrays.asList(
           new Book("Silmarilion", "Tolkien", "Fantasy", 10, Currency.PLN, 600 ),
           new Book("Zamek", "Kafka", "Drama", 18, Currency.PLN, 246 ),
           new Book("Władca much", "Golding", "War Drama", 21, Currency.PLN, 222 ),
           new Book("Mechaniczna Pomarańcza", "Burgess", "Powieść", 12, Currency.PLN, 159 ),
           new Book("Król Szczurów", "Clavell", "Powieść", 17, Currency.PLN, 419 )
   ));

   private Collection<Book> filteredBooks;

   public boolean filterByPrice(Object value, Object filter) {
      System.out.println("int filter by price");
      String filterText = (filter == null) ? null : filter.toString().trim();

      if(filterText == null||filterText.equals("")) return true;
      if(value == null) return false;
      return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
   }

   public Collection<Book> getBooks() {
      return books;
   }

   public void setFilteredBooks(Collection<Book> filteredBooks) {
      this.filteredBooks = filteredBooks;
   }

   public Collection<Book> getFilteredBooks() {
      return filteredBooks;
   }
}
