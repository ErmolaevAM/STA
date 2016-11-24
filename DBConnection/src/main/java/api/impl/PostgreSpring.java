package api.impl;

import api.DBConnection;
import entity.Apps;
import entity.StackTrace;
import entity.Table;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 23.11.2016.
 */
public class PostgreSpring implements DBConnection {
    EntityManager entityManager;
    public PostgreSpring() {
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

    public List<Table> getAllInformation() {
        return null;
    }

}
