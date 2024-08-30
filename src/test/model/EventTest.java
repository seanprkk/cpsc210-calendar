package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    private Time t1, t3, t5;
    private Event e2, e4;

    @BeforeEach
    public void setup() {
        t1 = new Time(2000, 5, 29, 21, 40);
        t3 = new Time(2001, 3, 29, 13, 12);
        t5 = new Time(2001, 10, 31, 21, 40);
        e2 = new Event(t1, t3);
        e4 = new Event(t3, t5);
    }

    @Test
    public void testConstructor() {
        assertEquals("New Event", e2.getTitle());
        assertEquals("No description.", e2.getDescription());
        assertEquals(t1, e2.getStartTime());
        assertEquals(t3, e2.getEndTime());
        assertEquals(0, e2.getPriority());
        assertEquals("New Event", e4.getTitle());
        assertEquals("No description.", e4.getDescription());
        assertEquals(t3, e4.getStartTime());
        assertEquals(t5, e4.getEndTime());
        assertEquals(0, e4.getPriority());
    }

    @Test
    public void testSetTitle() {
        e2.setTitle("foo");
        assertEquals("foo", e2.getTitle());
    }

    @Test
    public void testSetDescription() {
        e2.setDescription("goo");
        assertEquals("goo", e2.getDescription());
    }

    @Test
    public void testSetPriority() {
        e2.setPriority(3);
        assertEquals(3, e2.getPriority());
    }

    @Test
    public void testSetStartTime() {
        Time newTime = new Time(2000, 12, 7, 5, 40);
        e2.setStartTime(newTime);
        assertEquals(newTime, e2.getStartTime());
    }

    @Test
    public void testSetEndTime() {
        Time newTime = new Time(2000, 12, 7, 5, 40);
        e2.setEndTime(newTime);
        assertEquals(newTime, e2.getEndTime());
    }

}
