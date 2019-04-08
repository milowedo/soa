package agh.soa.jpa.beans;

import agh.soa.jpa.DAO.BookDAO;
import agh.soa.jpa.entities.Book;
import org.primefaces.event.RowEditEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "BookBean")
@ViewScoped
public class BookBean implements Serializable {

   private BookDAO bookDAO = new BookDAO();

   private List<Book> books = bookDAO.getBooks();


   public void onRowEdit(RowEditEvent event) {
       int editedBookID = (((Book) event.getObject()).getId());
       if(editedBookID == 0) this.saveNewBook((Book) event.getObject());

       for(Book book : books){
           if(book.getId() == editedBookID) {
               books.set(books.indexOf(book),((Book) event.getObject()));
               break;
           }
       }
       bookDAO.update(editedBookID, ((Book) event.getObject()));

   }

   private void saveNewBook(Book book) {
      try {
         bookDAO.saveNewBook(book);
      } catch (Exception e) {
         System.err.print("Book with such ISBN or title already exists!");
      }
   }

   public void delete(int bookId) {
       for(Book book : books){
           if(book.getId() == bookId) {
               books.remove(book);
               break;
           }
       }
       bookDAO.delete(bookId);
   }

   public void newBook() {
       Book newBook = new Book();
       this.books.add(newBook);
   }




   //
   //filtering etc
   //
   private Collection<Book> filteredBooks;

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
