package agh.soa.library.DAO;

import agh.soa.library.beans.ConfirmationQueueProducer;
import com.agh.soa.daoInterfaces.IBookDAO;
import com.agh.soa.entity.Book;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.*;

public class BookDAO implements IBookDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("postgres");
    private EntityManager em = factory.createEntityManager();

    private List books;

    @Inject
    private ConfirmationQueueProducer confirmationQueueProducer;

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

    public void saveNewBook(Book b, String user) {
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
        confirmationQueueProducer.sendMessage("\""+b.getTitle() +"\" added.",user);
    }
    }

    public void update(int id, Book book) {
        em.getTransaction().begin();
        getOne(id).ifPresent(old -> em.merge(book));
        em.getTransaction().commit();
    }

    public void delete(int id, String title, String user) {
        em.getTransaction().begin();
        getOne(id).ifPresent(em::remove);
        em.getTransaction().commit();
        confirmationQueueProducer.sendMessage("\""+title+ "\" deleted.", user);

    }

    public List getBooks() {
        return books;
    }

    @Override
    public void borrow(int id, Book book, String user) {
        this.update(id, book);
        if(book.isBorrowed()){
            confirmationQueueProducer.sendMessage("\""+book.getTitle()+ "\" borrowed.", user);
        }else confirmationQueueProducer.sendMessage("\""+book.getTitle()+ "\" returned.", user);

    }

}
