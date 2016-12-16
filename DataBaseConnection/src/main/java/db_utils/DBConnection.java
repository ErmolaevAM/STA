package db_utils;

import entities.Apps;
import entities.Pair;
import entities.StackTrace;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Александр on 27.11.2016.
 */
public interface DBConnection {
    List<Apps> getAllApplications();

    List<StackTrace> getAllStackTraces();

    List<Pair> getAllPairs();
}
