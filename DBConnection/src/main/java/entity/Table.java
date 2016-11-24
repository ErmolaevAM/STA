package entity;

/**
 * Created by Александр on 22.11.2016.
 */
public class Table {
    private Apps app;
    private StackTrace stackTrace;

    public Table(Apps app, StackTrace stackTrace) {
        this.app = app;
        this.stackTrace = stackTrace;
    }


    public StackTrace getStackTrace() {
        return stackTrace;
    }

    public Apps getApp() {
        return app;
    }

    public void printInfo(){
        System.out.println("AppID: " + this.app.getAppId() + " App name: " + this.app.getAppName());
        System.out.println("ST ID: " + this.stackTrace.getStackTraceId() + " ST title: " + this.stackTrace.getStackTraceTitle()
                + " ST: " + this.stackTrace.getStackTrace());
    }

}
