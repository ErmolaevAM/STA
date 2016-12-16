package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.math.BigDecimal;

/**
 * Created by Александр on 16.12.2016.
 */

public class Pair {
    @Column(name = "app_id")
    private int first;

    @Column(name = "st_id")
    private int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
