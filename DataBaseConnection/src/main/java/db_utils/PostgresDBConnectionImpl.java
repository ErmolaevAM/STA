package db_utils;

import entities.Apps;
import entities.StackTrace;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Александр on 27.11.2016.
 */
public class PostgresDBConnectionImpl implements DBConnection {
    EntityManager entityManager;
    public PostgresDBConnectionImpl() {
        this.entityManager = Persistence.createEntityManagerFactory("Postgresql").createEntityManager();
    }

    public List<Apps> getAllApplications() {
        List<Apps> appsList;
        entityManager.getTransaction().begin();
        appsList = entityManager.createNamedQuery("Apps.getAll", Apps.class).getResultList();
        entityManager.getTransaction().commit();
        return appsList;
    }

    public List<StackTrace> getAllStackTraces() {
        List<StackTrace> stList;
        entityManager.getTransaction().begin();
        stList = entityManager.createNamedQuery("ST.getAll", StackTrace.class).getResultList();
        entityManager.getTransaction().commit();
        return stList;
    }

}
