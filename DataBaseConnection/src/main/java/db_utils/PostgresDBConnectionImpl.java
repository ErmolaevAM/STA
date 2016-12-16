package db_utils;

import entities.Apps;
import entities.Pair;
import entities.StackTrace;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
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

        List<Pair> list = getAllPairs();
        for (int i = 0; i < appsList.size(); i++) {
            List<Integer> stList = new ArrayList<Integer>();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getFirst() == appsList.get(i).getAppId()){
                    stList.add(list.get(j).getSecond());
                    appsList.get(i).setRankedCoeff(appsList.get(i).getRankedCoeff()+1);
                }
            }
            appsList.get(i).setStackTraceId(stList);
        }

        return appsList;
    }

    public List<StackTrace> getAllStackTraces() {
        List<StackTrace> stList;
        entityManager.getTransaction().begin();
        stList = entityManager.createNamedQuery("ST.getAll", StackTrace.class).getResultList();
        entityManager.getTransaction().commit();

        List<Pair> list = getAllPairs();
        for (int i = 0; i < stList.size(); i++) {
            List<Integer> appList = new ArrayList<Integer>();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSecond() == stList.get(i).getStackTraceId()){
                    appList.add(list.get(j).getFirst());
                    stList.get(i).setRankedCoeff(stList.get(i).getRankedCoeff()+1);
                }
            }
            stList.get(i).setAppsList(appList);
        }

        return stList;
    }

    public List<Pair> getAllPairs() {
        List<Pair> pairs = new ArrayList<Pair>();
        List<Integer> first;
        List<Integer> second;
        entityManager.getTransaction().begin();
        first = entityManager.createNativeQuery("SELECT app_id FROM apps_reqs").getResultList();
        second = entityManager.createNativeQuery("SELECT st_id FROM apps_reqs").getResultList();

        for (int i=0;i<first.size();i++){
            pairs.add(new Pair(first.get(i), second.get(i)));
        }

        entityManager.getTransaction().commit();
        return pairs;
    }
}
