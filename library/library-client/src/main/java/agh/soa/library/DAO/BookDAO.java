package agh.soa.library.DAO;

import com.agh.soa.daoInterfaces.IBookDAO;
import com.agh.soa.entity.Book;


import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.*;

public class BookDAO implements IBookDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("postgres");
    private EntityManager em = factory.createEntityManager();

    private List<Book> books;

    @PostConstruct
    private void init(){
        books = this.getAll();
    }

    public BookDAO(){ }

    public List<Book> getAll() {
        em.getTransaction().begin();
        try {
            Query bc = em.createQuery("From Book where price = 3");
            System.out.println(bc.getFirstResult());
            Query q = em.createQuery("FROM Book", Book.class);
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
        em.persist(b);
        em.getTransaction().commit();
    }

    public void update(int id, Book book) {
        em.getTransaction().begin();
        getOne(id).ifPresent(old -> em.merge(book));
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        getOne(id).ifPresent(em::remove);
        em.getTransaction().commit();
    }

    public List<Book> getBooks() {
        return books;
    }

}
