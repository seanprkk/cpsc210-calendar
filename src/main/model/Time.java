package model;

import org.json.JSONObject;
import persistence.Writable;

/*
Represents a specific time.
 */
public class Time implements Writable {

    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private String stringRep;
    private long numRep;

    // REQUIRES: year is within [0, INF]; month is within [1,12]; day exists within given month;
    //           hour is within [0,23]; minute is within [0,59]
    // EFFECTS: creates new time with given hr, min, yr, month, and day. Number and String representations are created.
    public Time(Integer year, Integer month, Integer day, Integer hour, Integer minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        resetRep();
    }

    // EFFECTS: returns true if this time occurs before the given time
    public boolean isBeforeOrEqualTo(Time t) {
        return (this.numRep <= t.getNumRep());
    }

    // REQUIRES: year is within [0, INF]
    // MODIFIES: this
    // EFFECTS: sets year
    public void setYear(Integer year) {
        this.year = year;
        resetRep();
    }

    // REQUIRES: month is within [1,12]
    // MODIFIES: this
    // EFFECTS: sets month
    public void setMonth(Integer month) {
        this.month = month;
        resetRep();
    }

    // REQUIRES: day exists within given month
    // MODIFIES: this
    // EFFECTS: sets day
    public void setDay(Integer day) {
        this.day = day;
        resetRep();
    }

    // REQUIRES: hour is within [0,23]
    // MODIFIES: this
    // EFFECTS: sets hour
    public void setHour(Integer hour) {
        this.hour = hour;
        resetRep();
    }

    // REQUIRES: minute is within [0,59]
    // MODIFIES: this
    // EFFECTS: sets minute
    public void setMinute(Integer min) {
        this.minute = min;
        resetRep();
    }

    // MODIFIES: this
    // EFFECTS: resets number and string representations of time with current fields
    private void resetRep() {
        long e2 = 100L;
        long e4 = 10000L;
        long e6 = 1000000L;
        long e8 = 100000000L;
        this.stringRep = this.hour.toString() + ":" + this.minute.toString() + ", " + this.month.toString() + "/"
                + this.day.toString() + "/" + this.year.toString();
        this.numRep = minute.longValue() + (e2 * hour.longValue()) + (e4 * day.longValue())
                + (e6 * month.longValue()) + (e8 * year.longValue());
    }

    // EFFECTS: returns the year
    public Integer getYear() {
        return this.year;
    }

    // EFFECTS: returns the month
    public Integer getMonth() {
        return this.month;
    }

    // EFFECTS: returns the day
    public Integer getDay() {
        return this.day;
    }

    // EFFECTS: returns the hour
    public Integer getHour() {
        return this.hour;
    }

    // EFFECTS: returns the minute
    public Integer getMinute() {
        return this.minute;
    }

    // EFFECTS: returns the string representation of this time
    public String getStringRep() {
        return this.stringRep;
    }

    // EFFECTS: returns the number representation of this time
    public long getNumRep() {
        return this.numRep;
    }

    // EFFECTS: creates JSONObject representation of this time.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("year", this.year);
        json.put("month", this.month);
        json.put("day", this.day);
        json.put("hour", this.hour);
        json.put("minute", this.minute);
        return json;
    }
}
