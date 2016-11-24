package api.impl; /**
 * Created by Александр on 17.11.2016.
 */
import api.DBConnection;
import entity.Apps;
import entity.StackTrace;
import entity.Table;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PostgreSQL implements DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgresql";
    public static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PostgreSQL() throws SQLException {
        /*connection = getPostgreConnection();
        System.out.println("Good");*/
    }

    public List<Apps> getAllApplications() {
        List<Apps> appList = new ArrayList<Apps>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM applications");

            while (result.next()){
                appList.add(new Apps(result.getInt(1),result.getString(2)));
            }
            System.out.println("Application list ready.");

            for (int i=0;i<appList.size();i++){
                appList.get(i).printInfo();
            }

        } catch (SQLException ex){
            ex.getStackTrace();
        }

        return appList;
    }

    public List<StackTrace> getAllStackTraces() {
        List<StackTrace> stackTraceList = new ArrayList<StackTrace>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM requests");

            while (result.next()){
                stackTraceList.add(new StackTrace(result.getInt(1), result.getString(2), result.getString(3)));
            }
            System.out.println("StackTrace list ready.");

            for (int i=0;i<stackTraceList.size();i++){
                stackTraceList.get(i).printInfo();
            }

        } catch (SQLException ex){
            ex.getStackTrace();
        }

        return stackTraceList;
    }

    public List<Table> getAllInformation() {
        List<Table> tableList = new ArrayList<Table>();
        try {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM apps_reqs");

            while (result.next()){
                System.out.println(result.getInt(1) + " : " + result.getInt(2));
            }

        } catch (SQLException ex){
            ex.getStackTrace();
        }
        return tableList;

    }
}
