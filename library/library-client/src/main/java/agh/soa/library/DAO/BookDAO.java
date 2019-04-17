package agh.soa.library.DAO;

import com.agh.soa.daoInterfaces.IBookDAO;
import com.agh.soa.entity.Book;


import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.*;

public class BookDAO implements IBookDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("postgres");
    private EntityManager em = factory.createEntityManager();

    private List books;

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
            String jpql = "SELECT id FROM Author WHERE surname = :surname";
            authorId = em.createQuery(jpql, Integer.class)
                    .setParameter("surname", b.getAuthor().getSurname())
                    .getSingleResult();
            b.getAuthor().setId(authorId);
        }catch (Throwable e){
            em.persist(b.getAuthor());
        }finally {
            em.persist(b);
            em.getTransaction().commit();

        }
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

    public List getBooks() {
        return books;
    }

}
