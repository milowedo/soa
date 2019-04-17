package agh.soa.library.DAO;

import com.agh.soa.daoInterfaces.IAuthorDAO;
import com.agh.soa.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AuthorDAO implements IAuthorDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("postgres");
    private EntityManager em = factory.createEntityManager();

    @Override
    public void addAuthor(Author author) {
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
    }

    @Override
    public Author getAuthorById(int id) {
        return null;
    }

    @Override
    public List getAllAuthors() {
        return null;
    }
}
