package agh.soa.library.DAO;

import com.agh.soa.daoInterfaces.IReaderDAO;
import com.agh.soa.entity.Reader;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReaderDAO implements IReaderDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("postgres");
    private EntityManager em = factory.createEntityManager();

    @Override
    public void addReader(Reader reader) {
        em.getTransaction().begin();
        em.persist(reader);
        em.getTransaction().commit();
    }

    @Override
    public Reader getReaderByID(int id) {
        em.getTransaction().begin();
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Reader> query = cb.createQuery(Reader.class);
            Root<Reader> hh = query.from(Reader.class);
            query.select(hh)
                    .where(cb.equal(hh.get("id"), 1L));
            TypedQuery<Reader> tq = em.createQuery(query);
            return tq.getSingleResult();
        }
//        try {
//            String jpql = "FROM Reader WHERE id = :id";
//            return em.createQuery(jpql, Reader.class)
//                    .setParameter("id", id)
//                    .getSingleResult();
//        }
        finally {
            em.getTransaction().commit();
        }
    }

    @Override
    public List getAllReader() {
        return null;
    }

    @Override
    public List getByAuthorAndTimeStamp(String surname, Date left, Date right) {
        Query query = em.createQuery("FROM Loan as l JOIN l.reader as r JOIN l.book as b JOIN b.author as a WHERE a.surname = :surname AND l.loanDate > :date1 AND l.loanDate < :date2");
        query.setParameter("surname", surname);
        query.setParameter("date1", left);
        query.setParameter("date2", right);
        List list = query.getResultList();

        for (Object a: list){
            System.out.println(a);
        }
        return list;

    }

    @Override
    public List getByBookName(String name) {
        Query query = em.createQuery("SELECT r FROM Loan as l JOIN l.reader as r JOIN l.book as b WHERE b.title = :title");
        query.setParameter("title", name);
        List list = query.getResultList();

        for (Object el: list){
            System.out.println(el);
        }
        return list;
    }
}
