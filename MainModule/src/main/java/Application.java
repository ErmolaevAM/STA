import db_utils.DBConnection;
import db_utils.PostgresDBConnectionImpl;
import elasticsearch.ElasticsearchController;
import entities.Apps;
import entities.Pair;
import entities.StackTrace;
import jsonEntities.JsonApps;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by Александр on 27.11.2016.
 */
public class Application {
    public static void main(String[] args) {
        DBConnection db = new PostgresDBConnectionImpl();


//нормально работает кусок 1
        List<Apps> appses = db.getAllApplications();
        System.out.println("Size appses = "+appses.size());
        for (int i=0;i<appses.size();i++){
            System.out.println(appses.get(i).getAppId() + " " + appses.get(i).getAppName() + " " + appses.get(i).getStackTraceId());
        }
        System.out.println("/////////////////////////////");
        List<Pair> pairs = db.getAllPairs();
        System.out.println("Size pairs= " + pairs.size());
        for (int i=0;i<pairs.size();i++){
            System.out.println(i+")"+"first="+pairs.get(i).getFirst()+", second="+pairs.get(i).getSecond()+";");
        }
        System.out.println("/////////////////////////////");
        List<StackTrace> list = db.getAllStackTraces();
        System.out.println("Size st = "+list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getStackTraceId()+" "+list.get(i).getStackTraceTitle()+" "+list.get(i).getStackTrace());
        }
//конец куска 1


//кусок 2
        try {
            ElasticsearchController esc = new ElasticsearchController(db);
            esc.postInfoToElastic();
            List<JsonApps> lst = esc.getAppByStacktraceSubstring("or1");

            for (JsonApps item : lst) {
                System.out.println(item.toString());
            }

            /*for (JsonApps item : lst){
                System.out.println("ID: "+item.getAppId()+"; App name: "+item.getAppName()+"; ST list: "+item.getStackTraceId()+"; Rank coeff: "+item.getRankedCoeff());
            }*/
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//конец куска 2




    }
}
