package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@javax.persistence.Table(name = "requests")
@NamedQuery(name = "ST.getAll", query = "SELECT a FROM StackTrace a")
public class StackTrace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "st_id")
    @JsonProperty("StacjTrace ID")
    private int stackTraceId;

    @Column(name = "st_title")
    @JsonProperty("StackTrace title")
    private String stackTraceTitle;

    @Column(name = "st")
    @JsonProperty("StackTrace")
    private String stackTrace;

    @ManyToMany(mappedBy = "stackTraces", fetch = FetchType.LAZY)
    @JsonProperty("Applications list")
    private List<Apps> apps;

    @Transient
    @JsonProperty("App list")
    private List<Integer> appsList;

    @Transient
    @JsonProperty("Ranked coeff")
    private int rankedCoeff = 0;

    public int getRankedCoeff() {
        return rankedCoeff;
    }

    public void setRankedCoeff(int rankedCoeff) {
        this.rankedCoeff = rankedCoeff;
    }

    public List<Integer> getAppsList() {
        return appsList;
    }

    public void setAppsList(List<Integer> appsList) {
        this.appsList = appsList;
    }

    public  StackTrace(int id, String title, String stackTrace){
        this.stackTraceId = id;
        this.stackTraceTitle = title;
        this.stackTrace = stackTrace;
    }

    public StackTrace() {
    }

    public StackTrace(String stackTraceTitle, String stackTrace, List<Apps> apps) {
        this.stackTraceTitle = stackTraceTitle;
        this.stackTrace = stackTrace;
        this.apps = apps;
    }

    public int getStackTraceId() {
        return stackTraceId;
    }

    public String getStackTraceTitle() {
        return stackTraceTitle;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public List<Apps> getApps() {
        return apps;
    }

    public void setApps(List<Apps> apps) {
        this.apps = apps;
    }

    public void printInfo(){
        System.out.println("ID: " + this.getStackTraceId() + " Title: " + this.getStackTraceTitle() + " ST: " + this.getStackTrace());
    }

    @Override
    public String toString() {
        return "StackTrace{" +
                "stackTraceId=" + stackTraceId +
                ", stackTraceTitle='" + stackTraceTitle + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                ", apps=" + apps +
                '}';
    }
}