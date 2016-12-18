package jsonEntities;

import com.google.gson.annotations.SerializedName;
import entities.Apps;
import entities.StackTrace;
import org.springframework.util.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 17.12.2016.
 */
public class JsonApps {
    @SerializedName("application_id")
    private int appId;

    @SerializedName("application_name")
    private String appName;

    @SerializedName("application_stacktrace")
    private List<Integer> stackTraceId;

    @SerializedName("app_sts")
    private List<String> stackTraces;

    public List<String> getStackTraces() {
        return stackTraces;
    }

    public void addStackTrace(String stackTrace) {
        this.stackTraces.add(stackTrace);
    }

    @SerializedName("application_ranked_coeff")
    private int rankedCoeff = 0;

    public JsonApps(Apps app) {
        this.appId = app.getAppId();
        this.appName = app.getAppName();
        this.stackTraceId = app.getStackTraceId();
        this.rankedCoeff = app.getRankedCoeff();
        this.stackTraces = new ArrayList<>();
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<Integer> getStackTraceId() {
        return stackTraceId;
    }

    public void setStackTraceId(List<Integer> stackTraceId) {
        this.stackTraceId = stackTraceId;
    }

    public int getRankedCoeff() {
        return rankedCoeff;
    }

    public void setRankedCoeff(int rankedCoeff) {
        this.rankedCoeff = rankedCoeff;
    }

    @Override
    public String toString() {
        return "JsonApps{" +
                "appId=" + appId +
                ", appName='" + appName + '\'' +
                ", stackTraceId=" + stackTraceId +
                ", rankedCoeff=" + rankedCoeff +
                '}';
    }
}
