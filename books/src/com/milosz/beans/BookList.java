package com.milosz.beans;

import com.milosz.entities.Book;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean(name = "BookList")
@ViewScoped
public class BookList implements Serializable {
   private Collection<Book> books = new ArrayList<>(Arrays.asList(
           new Book("Silmarilion", "Tolkien", "Fantasy", 10, "PLN", 600 ),
           new Book("Zamek", "Kafka", "Drama", 18, "PLN", 246 ),
           new Book("Władca much", "Golding", "War Drama", 21, "PLN", 222 ),
           new Book("Mechaniczna Pomarańcza", "Burgess", "Powieść", 5, "USD", 159 ),
           new Book("Ja, Klaudiusz", "Graves", "Powieść", 22, "PLN", 412 ),
           new Book("Oliver Twist", "Dickens", "Powieść", 12, "PLN", 417 ),
           new Book("Little Women", "Alcott", "Powieść", 4, "USD", 159 ),
           new Book("Wojna światów", "Wells", "Science fiction", 15, "PLN", 135 ),
           new Book("Wehikuł czasu", "Wells", "Science fiction", 23, "PLN", 90 ),
           new Book("Sen nocy letniej", "Szekspir", "Dramat", 3, "USD", 155 ),
           new Book("Król Szczurów", "Clavell", "Powieść", 4, "EUR", 419 )
   ));

   private Collection<Book> filteredBooks;
   private Collection<Book> originalCurrencyBooks;

   public boolean filterByPrice(Object value, Object filter, Locale locale) {
      String filterText = (filter == null) ? null : filter.toString().trim();

      if(filterText == null||filterText.equals("")) return true;
      if(value == null) return false;
      return ((Comparable) value).compareTo(Integer.valueOf(filterText)) <=0 ;
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

   public List<String> getCurrencies(){
      return new ArrayList<>(Arrays.asList("PLN","USD","EUR"));
   }

   public void convertToPln() {
      originalCurrencyBooks = new ArrayList<Book>(books.size());
      for(Book b : books) ((ArrayList<Book>) originalCurrencyBooks).add(b.clone());
      this.books.stream().map(BookList::convertSingleToPln).collect(Collectors.toList());
   }

   public void convertToOriginal() {
      this.books = this.originalCurrencyBooks;
   }

   static Book convertSingleToPln(Book book){
      Integer currentPrice;
      if(book.getCurrency().equals("PLN")){
         return book;

      }else if(book.getCurrency().equals("USD")){
         currentPrice = book.getPrice();
         book.setPrice(currentPrice*3);

      }else {
         currentPrice = book.getPrice();
         book.setPrice(currentPrice*4);
      }

      book.setCurrency("PLN");
      return book;
   }

   public String finalizeBooks() {
      return "summary";
   }
}
