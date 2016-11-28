import db_utils.DBConnection;
import db_utils.PostgresDBConnectionImpl;
import entities.Apps;

import java.util.List;

/**
 * Created by Александр on 27.11.2016.
 */
public class Application {
    public static void main(String[] args) {
        DBConnection postgre = new PostgresDBConnectionImpl();
        List<Apps> appsList = postgre.getAllApplications();
        for (Apps app:appsList ) {
            app.printInfo();
        }
    }
}
