package agh.soa.library.DAO;

import com.agh.soa.daoInterfaces.ILoansDAO;
import com.agh.soa.entity.Book;
import com.agh.soa.entity.Loan;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class LoanDAO implements ILoansDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("postgres");
    private EntityManager em = factory.createEntityManager();

    private List loans;

    @PostConstruct
    private void init(){
        loans = this.getAll();
    }

    @Override
    public List getAll() {
        em.getTransaction().begin();
        try {
            Query q = em.createQuery("from Loan", Loan.class);
            return q.getResultList();
        } catch (Exception e) {
            System.err.println("Database is empty" + e);
        }finally {
            em.flush();
            em.clear();
        }
        return null;
    }

    @Override
    public void updateLoan(Loan loan) {
        em.getTransaction().begin();
        em.merge(loan);
        em.getTransaction().commit();
    }

    @Override
    public Loan getLoanByBookID(Book id) {
        em.getTransaction().begin();
        try {
            String jpql = "FROM Loan WHERE book = :book";
            return em.createQuery(jpql, Loan.class)
                    .setParameter("book", id)
                    .getSingleResult();
        }catch (Throwable e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addLoan(Loan loan) {
        em.getTransaction().begin();
        em.persist(loan);
        em.getTransaction().commit();
    }

    public List getLoans() {
        return loans;
    }

}
