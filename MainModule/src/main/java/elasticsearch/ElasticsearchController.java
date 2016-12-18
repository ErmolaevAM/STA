package elasticsearch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db_utils.DBConnection;
import db_utils.PostgresDBConnectionImpl;
import entities.Apps;
import entities.StackTrace;
import jsonEntities.JsonApps;
import jsonEntities.JsonStackTrace;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
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

    private Gson gson;

    public ElasticsearchController(DBConnection dataInf) throws UnknownHostException {
        this.settings = Settings.builder().put("cluster.name", "my-application").build();
        this.transportClient = new PreBuiltTransportClient(settings);
        this.client = transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        this.dataInf = dataInf;
        this.gson = new GsonBuilder().create();
    }

    public ElasticsearchController() throws UnknownHostException {
        this.settings = Settings.builder().put("cluster.name", "my-application").build();
        this.transportClient = new PreBuiltTransportClient(settings);
        this.client = transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        this.dataInf = new PostgresDBConnectionImpl();
        this.gson = new GsonBuilder().create();
    }

    public void postInfoToElastic() throws IOException {
        List<Apps> appsList = dataInf.getAllApplications();
        List<StackTrace> stackTraceList = dataInf.getAllStackTraces();
        IndexResponse response;
        JsonApps app;
        JsonStackTrace stackTrace;


        for (int i = 0; i < appsList.size(); i++) {
            app = new JsonApps(appsList.get(i));
            response = client.prepareIndex("applications", "apps", String.valueOf(app.getAppId())).setSource(jsonBuilder().startObject()
                    .field("application_id", app.getAppId())
                    .field("application_name", app.getAppName())
                    .field("application_stacktrace", app.getStackTraceId())
                    .field("application_ranked_coeff", app.getRankedCoeff())
                    .endObject())
                    .get();
        }

        for (int i = 0; i < stackTraceList.size(); i++) {
            stackTrace = new JsonStackTrace(stackTraceList.get(i));
            response = client.prepareIndex("stack_traces", "stack_trace", String.valueOf(stackTrace.getStackTraceId())).setSource(jsonBuilder().startObject()
                    .field("stack_trace_id", stackTrace.getStackTraceId())
                    .field("stack_trace_title", stackTrace.getStackTraceTitle())
                    .field("stack_trace", stackTrace.getStackTrace())
                    .field("stacktrace_applications", stackTrace.getAppsList())
                    .field("stacktrace_ranked_coeff", stackTrace.getRankedCoeff())
                    .endObject())
                    .get();

        }

        System.out.println("Data posted.");


    }

    public List<JsonApps> getTopBrokenApps(){
        List<JsonApps> top = new ArrayList<>();

        SearchResponse response = client.prepareSearch("applications").setTypes("apps")
                .addSort("application_ranked_coeff", SortOrder.DESC)
                .execute()
                .actionGet();

        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            String str = hit.getSourceAsString();
            if (str != null){
                top.add(gson.fromJson(str, JsonApps.class));
            }
        }
        return top;
    }

    public List<JsonApps> getTopBrokenApps(int count){
        List<JsonApps> top = new ArrayList<>();

        SearchResponse response = client.prepareSearch("applications").setTypes("apps")
                .addSort("application_ranked_coeff", SortOrder.DESC)
                .setSize(count)
                .execute()
                .actionGet();

        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            String str = hit.getSourceAsString();
            if (str != null){
                top.add(gson.fromJson(str, JsonApps.class));
            }
        }
        return top;
    }

    public List<JsonStackTrace> getTopErrors(){
        List<JsonStackTrace> top = new ArrayList<>();

        SearchResponse response = client.prepareSearch("stack_traces").setTypes("stack_trace")
                .addSort("stacktrace_ranked_coeff", SortOrder.DESC)
                .execute()
                .actionGet();

        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            String str = hit.getSourceAsString();
            if (str != null){
                top.add(gson.fromJson(str, JsonStackTrace.class));
            }
        }
        return top;
    }

    public List<JsonStackTrace> getTopErrors(int count){
        List<JsonStackTrace> top = new ArrayList<>();

        SearchResponse response = client.prepareSearch("stack_traces").setTypes("stack_trace")
                .addSort("stacktrace_ranked_coeff", SortOrder.DESC)
                .setSize(count)
                .execute()
                .actionGet();

        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            String str = hit.getSourceAsString();
            if (str != null){
                top.add(gson.fromJson(str, JsonStackTrace.class));
            }
        }
        return top;
    }

    public List<JsonApps> getAppByStacktraceSubstring(String substring){
        List<JsonApps> returnList = new ArrayList<>();
        List<JsonStackTrace> jstList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        SearchResponse response;

        response = client.prepareSearch("stack_traces").setTypes("stack_trace")
                .setQuery(QueryBuilders.wildcardQuery("stack_trace", "*"+substring+"*"))
                .execute()
                .actionGet();

        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            String str = hit.getSourceAsString();
            if ( str != null){
                //System.out.println(str); check result
                jstList.add(gson.fromJson(str, JsonStackTrace.class));
            }
        }

        for (JsonStackTrace item : jstList) {
            for (Integer index : item.getAppsList()) {
                if (!list.contains(index)){
                    //System.out.println("index to added = "+ index); check result
                    list.add(index);
                }
            }
        }

        for (Integer index : list) {
            response = client.prepareSearch("applications").setTypes("apps")
                    .setQuery(QueryBuilders.termQuery("application_id", index))
                    .execute()
                    .actionGet();
            //System.out.println("mark1"); check result
            SearchHit[] result = response.getHits().getHits();
            for (SearchHit hit : result) {
                String str = hit.getSourceAsString();
                if(str != null){
                    //System.out.println(str); check result
                    returnList.add(gson.fromJson(str, JsonApps.class));
                }
            }

        }

        return returnList;
    }



}

