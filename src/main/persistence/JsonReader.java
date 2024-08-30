package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Workroom read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Workroom parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Workroom wr = new Workroom(name);
        addCalendars(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses calendars from JSON object and adds them to workroom
    private void addCalendars(Workroom wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("calendars");
        for (Object json : jsonArray) {
            JSONObject nextCalendar = (JSONObject) json;
            addCalendar(wr, nextCalendar);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses calendar from JSON object and adds it to workroom
    private void addCalendar(Workroom wr, JSONObject jsonObject) {
        String name = jsonObject.getString("title");
        ArrayList<Event> events = addEvents(jsonObject);
        Calendar calendar = new Calendar();
        calendar.setTitle(name);
        for (Event e : events) {
            calendar.addEvent(e);
        }
        wr.addCalendar(calendar);
    }

    // MODIFIES: wr
    // EFFECTS: returns list of events parsed from JSON object
    private ArrayList<Event> addEvents(JSONObject calendarObject) {
        JSONArray eventArray = calendarObject.getJSONArray("events");
        ArrayList<Event> eventList = new ArrayList<Event>();
        for (Object json : eventArray) {
            JSONObject nextEvent = (JSONObject) json;
            eventList.add(produceEvent(nextEvent));
        }
        return eventList;
    }

    // EFFECTS: returns event parsed from JSON Object
    private Event produceEvent(JSONObject eventObject) {
        String title = eventObject.getString("title");
        String description = eventObject.getString("description");
        Time startTime = getTime(eventObject.getJSONObject("start time"));
        Time endTime = getTime(eventObject.getJSONObject("end time"));
        int priority = eventObject.getInt("priority");
        Event thisEvent = new Event(startTime, endTime);
        thisEvent.setTitle(title);
        thisEvent.setDescription(description);
        thisEvent.setPriority(priority);
        return thisEvent;
    }

    // EFFECTS: returns time parsed from JSON Object
    private Time getTime(JSONObject timeObject) {
        Integer year = timeObject.getInt("year");
        Integer month = timeObject.getInt("month");
        Integer day = timeObject.getInt("day");
        Integer hour = timeObject.getInt("hour");
        Integer minute = timeObject.getInt("minute");
        return new Time(year, month, day, hour, minute);
    }

}
