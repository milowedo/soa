package agh.soa.library.beans;


import com.agh.soa.daoInterfaces.IAuthorDAO;
import com.agh.soa.daoInterfaces.IBookDAO;
import com.agh.soa.daoInterfaces.ILoansDAO;
import com.agh.soa.daoInterfaces.IReaderDAO;
import com.agh.soa.entity.Author;
import com.agh.soa.entity.Book;
import com.agh.soa.entity.Loan;
import com.agh.soa.entity.Reader;
import org.primefaces.event.RowEditEvent;
import javax.enterprise.context.SessionScoped;
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

   @Inject
    public LibraryBean(IBookDAO bookDAO, ILoansDAO loansDAO, IReaderDAO readerDAO, IAuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.loansDAO = loansDAO;
        this.readerDAO = readerDAO;
        this.authorDAO = authorDAO;
        this.books = this.bookDAO.getBooks();
        this.loans = this.loansDAO.getLoans();
    }

    public void szerego() {
        this.reader = readerDAO.getReaderByID(2);
    }
    public void kowalski() {
        this.reader = readerDAO.getReaderByID(1);
    }

    public Reader getReader() {
        return reader;
    }

    public void borrowBook(Book book){
        book.setBorrowed(true);
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setReader(this.reader);
        loan.setLoanDate(new Date());
        loansDAO.addLoan(loan);
        loans.add(loan);
        bookDAO.update(book.getId(), book);
        bookDAO.borrow(book, reader);
    }
    public void returnBook(Book book){
        book.setBorrowed(false);
        Loan loan = loansDAO.getLoanByBookID(book);
        loan.setReturnDate(new Date());
        loansDAO.updateLoan(loan);
        bookDAO.update(book.getId(), book);
        bookDAO.borrow(book, reader);
        for(Loan el : loans){
            if(el.getBook() == loan.getBook() && el.getReader() == loan.getReader() && el.getLoanDate() == loan.getLoanDate()){
                el.setReturnDate(new Date());
            }
        }
    }


    //
    //CRUD related methods with books in library
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
         bookDAO.saveNewBook(book);
      } catch (Exception e) {
         System.err.print("Book with such title already exists!");
      }
   }
   public void deleteBook(int bookId) {
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
    // loans related
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
