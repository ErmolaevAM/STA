import api.DBConnection;
import api.impl.PostgreSQL;
import java.sql.SQLException;
import java.util.List;

import api.impl.PostgreSpring;
import entity.Apps;
import entity.StackTrace;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class Application {
    public static void main(String[] args) {
        /*EntityManager entityManager = Persistence.createEntityManagerFactory("Postgresql").createEntityManager();
        entityManager.getTransaction().begin();

        for (int i = 0; i < 10; i++) {
            Apps apps = new Apps("testApp" + i);
            entityManager.merge(apps);
        }


        List<Apps> apps = entityManager.createNamedQuery("Apps.getAll", Apps.class).getResultList();
        for (Apps app : apps) {
            System.out.println(app.getAppId() + " " + app.getAppName());
        }

        entityManager.getTransaction().commit();*/

        DBConnection postgreSQL = new PostgreSpring();
        List<Apps> lst = postgreSQL.getAllApplications();
        for (Apps o : lst) {
            System.out.println(o.getAppId() + " " + o.getAppName());
        }

        List<StackTrace> lst1 = postgreSQL.getAllStackTraces();
        for (StackTrace st : lst1) {
            System.out.println(st.getStackTraceId() + " " + st.getStackTraceTitle() + " " +st.getStackTrace());
        }

    }
}
