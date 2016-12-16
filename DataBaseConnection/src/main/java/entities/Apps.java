package entities;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@javax.persistence.Table(name = "apps")
@NamedQuery(name = "Apps.getAll", query = "SELECT a FROM Apps a")
public class Apps {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "app_id")
    @JsonProperty("Application ID")
    private int appId;

    @Column(name = "app_name")
    @JsonProperty("Application name")
    private String appName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "apps_reqs",
            joinColumns = @JoinColumn(name = "app_id", referencedColumnName = "app_id"),
            inverseJoinColumns = @JoinColumn(name = "st_id", referencedColumnName = "st_id"))
    @JsonProperty("StackTrace list")
    private List<StackTrace> stackTraces;

    @Transient
    @JsonProperty("ST list")
    private List<Integer> stackTraceId;

    @Transient
    @JsonProperty("Ranked coeff")
    private int rankedCoeff = 0;

    public int getRankedCoeff() {
        return rankedCoeff;
    }

    public void setRankedCoeff(int rankedCoeff) {
        this.rankedCoeff = rankedCoeff;
    }

    public List<Integer> getStackTraceId() {
        return stackTraceId;
    }

    public void setStackTraceId(List<Integer> stackTraceId) {
        this.stackTraceId = stackTraceId;
    }

    public Apps() {
    }

    public Apps(int id, String appName) {
        this.appName = appName;
        this.appId = id;
    }

    public Apps(String name){

        this.appName=name;
    }

    public List<StackTrace> getStackTraces() {
        return stackTraces;
    }

    public void setStackTraces(List<StackTrace> stackTraces) {
        this.stackTraces = stackTraces;
    }

    public int getAppId() {
        return appId;
    }

    public String getAppName() {
        return appName;
    }

    public void printInfo(){
        System.out.println("ID: "+ this.getAppId() + " App name: " + this.getAppName());
    }

    @Override
    public String toString() {
        return "Apps{" +
                "appId=" + appId +
                ", appName='" + appName + '\'' +
                ", stackTraces=" + stackTraces +
                '}';
    }
}
