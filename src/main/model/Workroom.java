package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a workroom having a collection of calendars
public class Workroom implements Writable {

    private String name;
    private List<Calendar> calendars;

    // EFFECTS: constructs workroom with a name and empty list of calendars
    public Workroom(String name) {
        this.name = name;
        calendars = new ArrayList<>();
    }

    // EFFECTS: returns name of this workroom
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds calendar to this workroom
    public void addCalendar(Calendar c) {
        calendars.add(c);
        ActionLog.getInstance().logAction(new Action("Added new calendar "
                + c.getTitle() + " to workroom."));
    }

    // MODIFIES: this
    // EFFECTS: removes calendar from this workroom
    public void removeCalendar(Calendar c) {
        calendars.remove(c);
        ActionLog.getInstance().logAction(new Action("Removed calendar \""
                + c.getTitle() + "\" from workroom."));
    }

    // EFFECTS: returns the list of calendars stored in this workroom
    public List<Calendar> getCalendars() {
        return calendars;
    }

    // EFFECTS: returns number of calendars in this workroom
    public int numCalendars() {
        return calendars.size();
    }

    // EFFECTS: creates JSONObject storing all the calendars of the application
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("calendars", calendarsToJson());
        return json;
    }

    // EFFECTS: returns calendars in this workroom as a JSON array
    private JSONArray calendarsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Calendar c : calendars) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

}
