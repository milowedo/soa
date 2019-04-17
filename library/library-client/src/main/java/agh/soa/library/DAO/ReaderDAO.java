package agh.soa.library.DAO;

import com.agh.soa.daoInterfaces.IReaderDAO;
import com.agh.soa.entity.Loan;
import com.agh.soa.entity.Reader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ReaderDAO implements IReaderDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("postgres");
    private EntityManager em = factory.createEntityManager();

    @Override
    public void addReader(Reader reader) {
        em.getTransaction().begin();
        System.out.println("adding reader");
        em.persist(reader);
        em.getTransaction().commit();
    }

    @Override
    public Reader getReaderByID(int id) {
        em.getTransaction().begin();
        String jpql = "FROM Reader WHERE id = :id";
        try {
            return em.createQuery(jpql, Reader.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }finally {
            em.getTransaction().commit();
        }


    }

    @Override
    public List getAllReader() {
        return null;
    }
}
