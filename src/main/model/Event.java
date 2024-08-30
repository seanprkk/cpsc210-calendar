package model;

import org.json.JSONObject;
import persistence.Writable;

/*
Represents a timely event.
*/
public class Event implements Writable {

    private Time startTime;
    private Time endTime;

    private String title;
    private String description;
    private int priority;

    // REQUIRES: startTime is before endTime
    // EFFECTS: sets up a new event with a default title, priority and description; given start and end times are set
    public Event(Time startTime, Time endTime) {
        this.title = "New Event";
        this.description = "No description.";
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = 0;
    }

    // EFFECTS: returns a JSONObject representation of Object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("start time", startTime.toJson());
        json.put("end time", endTime.toJson());
        json.put("priority", this.priority);
        return json;
    }

    // MODIFIES: this
    // EFFECTS: sets title of event
    public void setTitle(String newTitle) {
        String prevTitle = this.title;
        this.title = newTitle;
        ActionLog.getInstance().logAction(new Action("Set event \"" + prevTitle
                + "\"'s title to \"" + newTitle + "\""));
    }

    // MODIFIES: this
    // EFFECTS: sets description of event
    public void setDescription(String newDescription) {
        this.description = newDescription;
        ActionLog.getInstance().logAction(new Action("Set event \"" + this.title
                + "\"'s description to \"" + this.description + "\""));
    }

    // REQUIRES: p is within [0,5]
    // MODIFIES: this
    // EFFECTS: sets priority of event
    public void setPriority(int p) {
        this.priority = p;
        ActionLog.getInstance().logAction(new Action("Set event \"" + this.title
                + "\"'s priority to \"" + this.priority + "\""));
    }

    // MODIFIES: this
    // EFFECTS: sets new starting time for event e
    public void setStartTime(Time newStartTime) {
        this.startTime = newStartTime;
    }

    // MODIFIES: this
    // EFFECTS: sets new ending time for event
    public void setEndTime(Time newEndTime) {
        this.endTime = newEndTime;
    }

    // EFFECTS: returns title of event
    public String getTitle() {
        return this.title;
    }

    // EFFECTS: returns description of event
    public String getDescription() {
        return this.description;
    }

    // EFFECTS: returns starting time of event
    public Time getStartTime() {
        return this.startTime;
    }

    // EFFECTS: returns ending time of event
    public Time getEndTime() {
        return this.endTime;
    }

    // EFFECTS: returns priority of event
    public int getPriority() {
        return this.priority;
    }
}
