package agh.soa.library.DAO;

import com.agh.soa.daoInterfaces.IAuthorDAO;
import com.agh.soa.entity.Author;
import com.agh.soa.entity.Reader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

    @Override
    public List getAuthorsByReader(Reader reader) {
        Query query = em.createQuery("SELECT a FROM Loan as l JOIN l.reader as r JOIN l.book as b JOIN b.author as a WHERE r.name = :name AND r.surname = :surname");
        query.setParameter("name", reader.getName());
        query.setParameter("surname", reader.getSurname());
        List list = query.getResultList();

        for (Object el: list){
            System.out.println(el);
        }

        return list;
    }
}
