package com.milosz.dao;


import com.milosz.entity.Movie;

import javax.persistence.*;
import java.util.*;
import java.util.logging.Logger;

public class MovieDao {

    Logger logger = Logger.getLogger(MovieDao.class.getName());

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("postgres");
    private EntityManager em = factory.createEntityManager();

    public List getAll() {
        em.getTransaction().begin();
        try {
            Query q = em.createQuery("from Movie", Movie.class);
            return q.getResultList();
        } catch (Exception e) {
            logger.warning("Database is empty" + e);
        }finally {
            em.flush();
            em.clear();
        }
        return null;
    }

    public Movie getMovieByTitle(String title) {
        em.getTransaction().begin();
        try {
            Query q = em.createQuery("from Movie where title = :title", Movie.class)
                    .setParameter("title", title);
            return  (Movie) q.getSingleResult();
        } catch (Exception e) {
            logger.warning("Database is empty" + e);
        }finally {
            em.flush();
            em.clear();
        }
        return null;
    }

    public Optional<Movie> get(int id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    public void save(Movie movie) {
        em.getTransaction().begin();
        try {
            em.persist(movie);
        }catch (Throwable e){
            logger.warning("Problem with saving movie, was not saved");
        }finally {
            em.getTransaction().commit();
        }
    }

    public void update(Movie book) {
        em.getTransaction().begin();
        get(book.getId()).ifPresent(old -> em.merge(book));
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        get(id).ifPresent(em::remove);
        em.getTransaction().commit();
    }


}
