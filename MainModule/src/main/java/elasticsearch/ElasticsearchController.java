package elasticsearch;

import db_utils.DBConnection;
import db_utils.PostgresDBConnectionImpl;
import entities.Apps;
import entities.Pair;
import entities.StackTrace;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


/**
 * Created by Александр on 07.12.2016.
 */
public class ElasticsearchController {
    private Settings settings;
    private TransportClient transportClient;
    private Client client;

    private DBConnection dataInf;

    public ElasticsearchController() throws UnknownHostException {
        this.settings = Settings.builder().put("cluster.name", "my-application").build();
        this.transportClient = new PreBuiltTransportClient(settings);
        this.client = transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        this.dataInf = new PostgresDBConnectionImpl();
    }

    public void postInfoToElastic() throws IOException {
        List<Apps> appsList = dataInf.getAllApplications();
        List<StackTrace> stackTraceList = dataInf.getAllStackTraces();
        IndexResponse response;

        /*for (int i = 0; i < appsList.size(); i++) {
            List<Integer> stList = new ArrayList<>();
            for (int j = 0; j < pairs.size(); j++) {
                if (pairs.get(j).getFirst() == appsList.get(i).getAppId()){
                    stList.add(pairs.get(j).getSecond());
                }
            }
            appsList.get(i).setStackTraceId(stList);
        }

        for (int i = 0; i < stackTraceList.size(); i++) {
            List<Integer> appList = new ArrayList<>();
            for (int j = 0; j < pairs.size(); j++) {
                if (pairs.get(j).getSecond() == stackTraceList.get(i).getStackTraceId()){
                    appList.add(pairs.get(j).getFirst());
                }
            }
            stackTraceList.get(i).setAppsList(appList);
        }*/

        for (Apps item : appsList) {
            response = client.prepareIndex("applications", "app", String.valueOf(item.getAppId())).setSource(jsonBuilder().startObject()
                    .field("application_name", item.getAppName())
                    .field("application_stacktrace", item.getStackTraces())
                    .field("application_stacktrace_size", item.getStackTraces().size())
                    .field("ranked_coeff", item.getRankedCoeff())
                    .endObject())
                    .get();
        }

        for (StackTrace item : stackTraceList) {
            response = client.prepareIndex("stack_traces", "stack_trace", String.valueOf(item.getStackTraceId())).setSource(jsonBuilder().startObject()
                    .field("stack_trace_title", item.getStackTraceTitle())
                    .field("stack_trace", item.getStackTrace())
                    .field("stacktrace_applications", item.getApps())
                    .field("stacktrace_applications_size", item.getApps().size())
                    .field("ranked_coeff", item.getRankedCoeff())
                    .endObject())
                    .get();
        }

        System.out.println("Data posted.");


    }

    public List<Apps> getTopFiveBrokenApps(){
        List<Apps> topFive = new ArrayList<>();
        //GetResponse response = client.prepareSearch("applications").setTypes("app").setQuery().setSize(5).get();
        SearchResponse response = client.prepareSearch("applications").setTypes("app")
                .addSort("ranked_coeff", SortOrder.ASC)
                .setSize(5)
                .get();


        return topFive;
    }






}

