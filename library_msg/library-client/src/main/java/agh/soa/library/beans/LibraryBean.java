package agh.soa.library.beans;


import com.agh.soa.daoInterfaces.*;
import com.agh.soa.entity.*;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Named("BookBean")
@SessionScoped
public class LibraryBean implements Serializable {

   private IBookDAO bookDAO;
   private ILoansDAO loansDAO;
   private IReaderDAO readerDAO;
   private IAuthorDAO authorDAO;
   private List<Book> books;
   private List<Loan> loans;
   private Reader reader;
   private AvailabilityTopicConsumer consumer;

   @Inject
    public LibraryBean(IBookDAO bookDAO,
                       ILoansDAO loansDAO,
                       IReaderDAO readerDAO,
                       IAuthorDAO authorDAO,
                       AvailabilityTopicConsumer consumer) {
        this.bookDAO = bookDAO;
        this.loansDAO = loansDAO;
        this.readerDAO = readerDAO;
        this.authorDAO = authorDAO;
        this.consumer = consumer;
    }

    @PostConstruct
    public void initializeData(){
        this.books = this.bookDAO.getBooks();
        this.loans = this.loansDAO.getLoans();
        consumer.subscribe();
    }

    public void receiveTopicMessage(){
       this.consumer.receiveMessage();
    }


    public void userLogin(long index){
       this.reader = readerDAO.getReaderByID(index);
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", this.reader);
    }
    public void userLogout() {
        this.reader = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    public Reader getReader() {
        return reader;
    }

    public void borrowBook(Book book){
        book.setBorrowed(true);
        Loan loan = new Loan(new java.sql.Date(System.currentTimeMillis()), book, this.reader);
        loansDAO.addLoan(loan);
        bookDAO.borrow(book.getId(), book,reader.getName()+reader.getSurname());
        loans.add(loan);
    }

    public void returnBook(Book book){
        book.setBorrowed(false);
        Loan loan = loansDAO.getLoanByBookID(book);
        loan.setReturnDate(new java.sql.Date(System.currentTimeMillis()));
        loansDAO.updateLoan(loan);
        bookDAO.borrow(book.getId(), book, reader.getName()+reader.getSurname());
        for(Loan el : loans){
            if(el.getBook().equals(loan.getBook())
                    && el.getReader().equals(loan.getReader())
                    && el.getLoanDate().equals(loan.getLoanDate())){
                el.setReturnDate(new java.sql.Date(System.currentTimeMillis()));
                break;
            }
        }
    }


    //
    //edit, save, delete methods with books in library
    //
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
         bookDAO.saveNewBook(book, reader.getName()+reader.getSurname());
      } catch (Exception e) {
         System.err.print("Such book already exists!");
      }
   }
   public void deleteBook(int bookId) {
       String bookTitle = null;
       for(Book book : books){
           if(book.getId() == bookId) {
               bookTitle = book.getTitle();
               books.remove(book);
               break;
           }
       }
       bookDAO.delete(bookId, bookTitle,reader.getName()+reader.getSurname());
   }
   public void newBook() {
       Book newBook = new Book();
       Author author = new Author();
       newBook.setAuthor(author);
       this.books.add(newBook);
   }


   //
   //books filtering etc
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

    //
    //loans getter
    //
    public List<Loan> getLoans() {
        return loans;
    }

    //
    //zadania
    //
    public List zadanie1(){
        return readerDAO.getByAuthorAndTimeStamp("Graves", new Date(2018, Calendar.JANUARY,1), new Date(2018,Calendar.MAY,1));
    }

    public List zadanie2(){
        return readerDAO.getByBookName("War of the worlds");
    }

    public List zadanie3(){
        return authorDAO.getAuthorsByReader(this.reader);
    }
}
