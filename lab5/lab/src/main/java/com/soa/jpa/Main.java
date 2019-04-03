package com.soa.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = factory.createEntityManager();
        try {
            Student s1 = new Student("adam", "nowak", new Date());
            Student s2 = new Student("marek", "kowalski", new Date());
            Student s3 = new Student("anna", "marchewka", new Date());
            em.getTransaction().begin();
            em.persist(s1);
            em.persist(s2);
            em.persist(s3);
            em.getTransaction().commit();
            System.out.println("Zapisano w bazie: " + s1);
            System.out.println("Zapisano w bazie: " + s2);
            System.out.println("Zapisano w bazie: " + s3);
        }
        catch(Exception e) {
            System.err.println("Blad przy dodawaniu rekordu: " + e);
        }
    }
}

