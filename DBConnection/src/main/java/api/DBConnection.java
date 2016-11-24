package api;

import entity.Apps;
import entity.StackTrace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 17.11.2016.
 */
public interface DBConnection {
    List<Apps> getAllApplications();

    List<StackTrace> getAllStackTraces();

    List<?> getAllInformation();
}
