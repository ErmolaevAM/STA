package jsonEntities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import entities.Apps;
import entities.StackTrace;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Александр on 17.12.2016.
 */
public class JsonStackTrace {
    @SerializedName("stack_trace_id")
    private int stackTraceId;

    @SerializedName("stack_trace_title")
    private String stackTraceTitle;

    @SerializedName("stack_trace")
    private String stackTrace;

    @SerializedName("stacktrace_applications")
    private List<Integer> appsList;

    @SerializedName("stacktrace_ranked_coeff")
    private int rankedCoeff = 0;

    public JsonStackTrace(StackTrace stackTrace) {
        this.stackTraceId = stackTrace.getStackTraceId();
        this.stackTraceTitle = stackTrace.getStackTraceTitle();
        this.stackTrace = stackTrace.getStackTrace();
        this.appsList = stackTrace.getAppsList();
        this.rankedCoeff = stackTrace.getRankedCoeff();
    }

    public int getStackTraceId() {
        return stackTraceId;
    }

    public void setStackTraceId(int stackTraceId) {
        this.stackTraceId = stackTraceId;
    }

    public String getStackTraceTitle() {
        return stackTraceTitle;
    }

    public void setStackTraceTitle(String stackTraceTitle) {
        this.stackTraceTitle = stackTraceTitle;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public List<Integer> getAppsList() {
        return appsList;
    }

    public void setAppsList(List<Integer> appsList) {
        this.appsList = appsList;
    }

    public int getRankedCoeff() {
        return rankedCoeff;
    }

    public void setRankedCoeff(int rankedCoeff) {
        this.rankedCoeff = rankedCoeff;
    }

    @Override
    public String toString() {
        return "JsonStackTrace{" +
                "stackTraceId=" + stackTraceId +
                ", stackTraceTitle='" + stackTraceTitle + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                ", appsList=" + appsList +
                ", rankedCoeff=" + rankedCoeff +
                '}';
    }
}
