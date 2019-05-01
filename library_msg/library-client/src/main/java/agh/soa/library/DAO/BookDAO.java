package agh.soa.library.DAO;

import agh.soa.library.beans.ConfirmationQueue;
import com.agh.soa.daoInterfaces.IBookDAO;
import com.agh.soa.entity.Book;
import com.agh.soa.entity.Loan;
import com.agh.soa.entity.Reader;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.*;

public class BookDAO implements IBookDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("postgres");
    private EntityManager em = factory.createEntityManager();

    private List books;

    @Inject
    private ConfirmationQueue confirmationQueue;

    @PostConstruct
    private void init(){
        books = this.getAll();
    }

    public List getAll() {
        em.getTransaction().begin();
        try {
            Query q = em.createQuery("from Book", Book.class);
            return q.getResultList();
        } catch (Exception e) {
            System.err.println("Database is empty" + e);
        }finally {
            em.flush();
            em.clear();
        }
        return null;
    }

    public Optional<Book> getOne(int id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    public void saveNewBook(Book b) {
    em.getTransaction().begin();
    int authorId;
    try {
        String authorQuery = "SELECT id FROM Author WHERE surname = :surname";
        authorId = em.createQuery(authorQuery, Integer.class)
                .setParameter("surname", b.getAuthor().getSurname())
                .getSingleResult();
        b.getAuthor().setId(authorId);
    }catch (Throwable e){
        em.persist(b.getAuthor());
    }finally {
        em.persist(b);
        em.getTransaction().commit();
        confirmationQueue.sendMessage(b.getTitle() +" saved.");
    }
    }

    public void update(int id, Book book) {
        em.getTransaction().begin();
        getOne(id).ifPresent(old -> em.merge(book));
        em.getTransaction().commit();
        confirmationQueue.sendMessage(book.getTitle() + " changed.");
    }

    public void delete(int id, String title) {
        em.getTransaction().begin();
        getOne(id).ifPresent(em::remove);
        em.getTransaction().commit();
        confirmationQueue.sendMessage(title+ " deleted.");

    }

    public List getBooks() {
        return books;
    }

    @Override
    public void borrow(int id, Book book) {
        this.update(id, book);
        if(book.isBorrowed()){
            confirmationQueue.sendMessage(book.getTitle()+ " borrowed.");
        }else confirmationQueue.sendMessage(book.getTitle()+ " returned.");

    }

}
