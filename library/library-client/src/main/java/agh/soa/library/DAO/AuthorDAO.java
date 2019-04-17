package agh.soa.library.DAO;

import com.agh.soa.daoInterfaces.IAuthorDAO;
import com.agh.soa.entity.Author;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public class AuthorDAO implements IAuthorDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("postgres");
    private EntityManager em = factory.createEntityManager();

    @Override
    public Optional<Author> getAuthorById(int id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public void saveNewAuthor(Author b) {
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
    }

    @Override
    public void updateExistingAuthor(int id, Author book) {
        em.getTransaction().begin();
        getAuthorById(id).ifPresent(old -> em.merge(book));
        em.getTransaction().commit();
    }

    @Override
    public void deleteAuthor(int id) {
        em.getTransaction().begin();
        getAuthorById(id).ifPresent(em::remove);
        em.getTransaction().commit();
    }

}
