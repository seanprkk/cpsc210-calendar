package persistence;

import model.Calendar;
import model.Event;
import model.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCalendar(Calendar c, String title) {
        assertEquals(title, c.getTitle());
    }

    protected void checkEvent(Event e, String title, String description, int priority) {
        assertEquals(title, e.getTitle());
        assertEquals(description, e.getDescription());
        assertEquals(priority, e.getPriority());
    }

    protected void checkTime(Time t, Integer year, Integer month, Integer day, Integer hour, Integer minute) {
        assertEquals(year, t.getYear());
        assertEquals(month, t.getMonth());
        assertEquals(day, t.getDay());
        assertEquals(hour, t.getHour());
        assertEquals(minute, t.getMinute());
    }
}