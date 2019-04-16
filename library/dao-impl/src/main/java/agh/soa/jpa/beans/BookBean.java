package agh.soa.jpa.beans;


import com.agh.soa.daoInterfaces.IBookDAO;
import com.agh.soa.entity.Book;
import org.primefaces.event.RowEditEvent;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Named("BookBean")
@SessionScoped
public class BookBean implements Serializable {

   private IBookDAO bookDAO;
   private List<Book> books;

   @Inject
    public BookBean(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
        this.books = this.bookDAO.getBooks();
    }

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
