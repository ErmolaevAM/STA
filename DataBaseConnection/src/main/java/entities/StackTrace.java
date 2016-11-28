package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@javax.persistence.Table(name = "requests")
@NamedQuery(name = "ST.getAll", query = "SELECT a FROM StackTrace a")
public class StackTrace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "st_id")
    private int stackTraceId;

    @Column(name = "st_title")
    private String stackTraceTitle;

    @Column(name = "st")
    private String stackTrace;

    @ManyToMany(mappedBy = "stackTraces", fetch = FetchType.EAGER)
    private List<Apps> apps;


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