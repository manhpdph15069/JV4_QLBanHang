package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    public static EntityManagerFactory getEntityManagerFactory (){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("database");
        return factory;
    }
    public static EntityManager getEntityManager(){
        EntityManager em = getEntityManagerFactory().createEntityManager();
        return em;
    }
}
