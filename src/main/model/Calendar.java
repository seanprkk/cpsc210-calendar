package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

/*
Represents a labelled calendar storing events.
*/
public class Calendar implements Writable {

    private String title;
    private ArrayList<Event> eventList;

    // EFFECTS: creates new calendar with given name, and an empty list.
    public Calendar() {
        this.title = "New Calendar";
        this.eventList = new ArrayList<Event>();
    }

    // MODIFIES: this
    // EFFECTS: adds an event to this calendar
    public void addEvent(Event e) {
        this.eventList.add(e);
        ActionLog.getInstance().logAction(new Action("Event \"" + e.getTitle()
                + "\" added to calendar \"" + this.title + "\""));
    }

    // REQUIRES: given event is already in this calendar
    // MODIFIES: this
    // EFFECTS: deletes an event from this calendar
    public void deleteEvent(Event e) {
        this.eventList.remove(e);
        ActionLog.getInstance().logAction(new Action("Event \"" + e.getTitle()
                + "\" removed from calendar \"" + this.title + "\""));
    }

    // EFFECTS: returns all events whose title starts with the given string
    public ArrayList<Event> searchEvents(String t) {
        ArrayList<Event> result = new ArrayList<Event>();
        for (Event e : this.eventList) {
            if (e.getTitle().toLowerCase().startsWith(t)) {
                result.add(e);
            }
        }
        return result;
    }

    // REQUIRES: valid year
    // EFFECTS: returns all events that contain corresponding year
    public ArrayList<Event> searchEvents(Integer year) {
        ArrayList<Event> matchingYear = new ArrayList<Event>();
        for (Event e : this.eventList) {
            if (yearIsWithin(e.getStartTime(), e.getEndTime(), year)) {
                matchingYear.add(e);
            }
        }
        return matchingYear;
    }

    // REQUIRES: valid year and month
    // EFFECTS: returns all events that contain corresponding month
    public ArrayList<Event> searchEvents(Integer year, Integer month) {
        ArrayList<Event> matchingYear = searchEvents(year);
        ArrayList<Event> matchingMonth = new ArrayList<Event>();
        for (Event e : matchingYear) {
            if (monthIsWithin(e.getStartTime(), e.getEndTime(), year, month)) {
                matchingMonth.add(e);
            }
        }
        return matchingMonth;
    }

    // REQUIRES: valid year, month and day
    // EFFECTS: returns all events that contain corresponding day
    public ArrayList<Event> searchEvents(Integer year, Integer month, Integer day) {
        ArrayList<Event> matchingMonth = searchEvents(year, month);
        ArrayList<Event> matchingDay = new ArrayList<>();
        for (Event e : matchingMonth) {
            if (dayIsWithin(e.getStartTime(), e.getEndTime(), year, month, day)) {
                matchingDay.add(e);
            }
        }
        return matchingDay;
    }

    // REQUIRES: valid year
    // EFFECTS: given year is within startTime and endTime
    private boolean yearIsWithin(Time startTime, Time endTime, int year) {
        Time minTime = new Time(year, 1, 1, 0, 0);
        Time maxTime = new Time(year, 12, 31, 23, 59);
        return !(startTime.getNumRep() > maxTime.getNumRep() || endTime.getNumRep() < minTime.getNumRep());
    }

    // EFFECTS: given year and month is within startTime and endTime
    private boolean monthIsWithin(Time startTime, Time endTime, int year, int month) {
        Time minTime = new Time(year, month, 1, 0, 0);
        Time maxTime = new Time(year, month, 31, 23, 59);
        return !(startTime.getNumRep() > maxTime.getNumRep() || endTime.getNumRep() < minTime.getNumRep());
    }

    // EFFECTS: given year, month and day is within startTime and endTime
    private boolean dayIsWithin(Time startTime, Time endTime, int year, int month, int day) {
        Time minTime = new Time(year, month, day, 0, 0);
        Time maxTime = new Time(year, month, day, 23, 59);
        return !(startTime.getNumRep() > maxTime.getNumRep() || endTime.getNumRep() < minTime.getNumRep());
    }

    // EFFECTS: creates JSONObject of this calendar
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray events = new JSONArray();
        for (Event e : eventList) {
            events.put(e.toJson());
        }
        json.put("title", title);
        json.put("events", events);
        return json;
    }

    // MODIFIES: this
    // EFFECTS: sets title of this calendar to given string
    public void setTitle(String title) {
        String prevTitle = this.title;
        this.title = title;
        ActionLog.getInstance().logAction(new Action("Set calendar \"" + prevTitle
                + "\"'s title to \"" + title + "\""));
    }


    // EFFECTS: returns title of calendar
    public String getTitle() {
        return this.title;
    }


    // EFFECTS: returns number of events in calendar
    public ArrayList<Event> getEvents() {
        return this.eventList;
    }

}
