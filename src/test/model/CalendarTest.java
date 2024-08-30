package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarTest {

    Calendar c1, c2;
    Event e1, e2, e3, e4;
    Time t1, t2, t3, t4;

    @BeforeEach
    public void setup() {

        t1 = new Time(2022, 6, 6, 13, 20);
        t2 = new Time(2022, 12, 31, 23, 59);
        t3 = new Time (2023, 4, 15, 6, 35);
        t4 = new Time (2023, 4, 17, 6, 35);

        e1 = new Event(t1, t2);
        e2 = new Event(t2, t3);
        e3 = new Event(t3, t4);
        e4 = new Event(t2, t4);

        e2.setTitle("foooo");
        e3.setTitle("goo");
        e4.setTitle("foo");

        c1 = new Calendar();
        c2 = new Calendar();

        c2.addEvent(e1);
        c2.addEvent(e2);
        c2.addEvent(e3);
        c2.addEvent(e4);
    }

    @Test
    public void testConstructor() {
        assertEquals("New Calendar", c1.getTitle());
        assertEquals("New Calendar", c2.getTitle());
    }

    @Test
    public void testAddEventSingle() {
        assertEquals(emptyList(), c1.getEvents());
        assertEquals(0, c1.getEvents().size());
        c1.addEvent(e1);
        ArrayList<Event> result = c1.getEvents();
        assertEquals(1, result.size());
        assertEquals(e1, result.get(0));
    }

    @Test
    public void testAddEventMultiple() {
        assertEquals(0, c1.getEvents().size());
        c1.addEvent(e1);
        c1.addEvent(e2);
        assertEquals(2, c1.getEvents().size());
        assertEquals(e1, c1.getEvents().get(0));
        assertEquals(e2, c1.getEvents().get(1));
    }

    @Test
    public void testDeleteEventSingle() {
        ArrayList<Event> result1 = new ArrayList<Event>();
        result1.add(e1);
        result1.add(e2);
        result1.add(e4);
        assertEquals(4, c2.getEvents().size());
        c2.deleteEvent(e3);
        assertEquals(3, c2.getEvents().size());
        assertEquals(result1, c2.getEvents());
    }

    @Test
    public void testDeleteEventMultiple() {
        ArrayList<Event> result1 = new ArrayList<Event>();
        result1.add(e1);
        result1.add(e2);
        result1.add(e4);
        assertEquals(4, c2.getEvents().size());
        c2.deleteEvent(e3);
        assertEquals(3, c2.getEvents().size());
        assertEquals(result1, c2.getEvents());
        c2.deleteEvent(e2);
        assertEquals(2, c2.getEvents().size());
        result1.remove(e2);
        assertEquals(result1, c2.getEvents());
    }

    @Test
    public void testSearchWithYearEmptyCalendar() {
        assertEquals(0, c1.searchEvents(2022).size());
    }

    @Test
    public void testSearchWithYearNoResultYear() {
        ArrayList<Event> result = c2.searchEvents(2005);
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchWithYearTestBound() {
        ArrayList<Event> result = c2.searchEvents(2022);
        assertEquals(3, result.size());
        assertEquals(e1, result.get(0));
        assertEquals(e2, result.get(1));
        assertEquals(e4, result.get(2));
    }

    @Test
    public void testSearchWithMonthEmptyCalendar() {
        assertEquals(0, c1.searchEvents(2022,3).size());
    }

    @Test
    public void testSearchWithMonthNoResultYear() {
        assertEquals(0, c1.searchEvents(2002,3).size());
    }

    @Test
    public void testSearchWithMonthNoResultMonthLow() {
        assertEquals(0, c1.searchEvents(2022,3).size());
    }

    @Test
    public void testSearchWithMonthNoResultMonthHigh() {
        assertEquals(0, c1.searchEvents(2023,6).size());
    }

    @Test
    public void testSearchWithMonthSingle() {
        ArrayList<Event> result = c2.searchEvents(2022,6);
        assertEquals(1, result.size());
        assertEquals(e1, result.get(0));
    }

    @Test
    public void testSearchWithMonthTestBound() {
        ArrayList<Event> result = c2.searchEvents(2023,4);
        assertEquals(3, result.size());
        assertEquals(e2, result.get(0));
        assertEquals(e3, result.get(1));
        assertEquals(e4, result.get(2));
        result = c2.searchEvents(2022,6);
        assertEquals(1, result.size());
        assertEquals(e1, result.get(0));
    }

    @Test
    public void testSearchWithMonthMultipleResults() {
        ArrayList<Event> result = c2.searchEvents(2023,2);
        assertEquals(2, result.size());
        assertEquals(e2, result.get(0));
        assertEquals(e4, result.get(1));
    }

    @Test
    public void testSearchWithDayEmptyCalendar() {
        assertEquals(0, c2.searchEvents(2022, 4, 15).size());
    }

    @Test
    public void testSearchWithDayNoResultYear() {
        assertEquals(0, c2.searchEvents(2002, 4, 15).size());
        assertEquals(0, c2.searchEvents(2202, 4, 15).size());
    }

    @Test
    public void testSearchWithDayNoResultMonth() {
        assertEquals(0, c2.searchEvents(2022, 4, 15).size());
        assertEquals(0, c2.searchEvents(2023, 12, 15).size());
    }

    @Test
    public void testSearchWithDayNoResultDay() {
        assertEquals(0, c2.searchEvents(2022, 6, 5).size());
        assertEquals(0, c2.searchEvents(2023, 4, 18).size());
    }

    @Test
    public void testSearchWithDaySingle() {
        ArrayList<Event> result = c2.searchEvents(2022,6, 6);
        assertEquals(1, result.size());
        assertEquals(e1, result.get(0));
    }

    @Test
    public void testSearchWithDayTestBound() {
        ArrayList<Event> result = c2.searchEvents(2022,6, 6);
        assertEquals(1, result.size());
        assertEquals(e1, result.get(0));
        result = c2.searchEvents(2023,4,17);
        assertEquals(2, result.size());
        assertEquals(e3, result.get(0));
        assertEquals(e4, result.get(1));
    }

    @Test
    public void testSearchWithDayMultipleResults() {
        ArrayList<Event> result = c2.searchEvents(2023,2,22);
        assertEquals(2, result.size());
        assertEquals(e2, result.get(0));
        assertEquals(e4, result.get(1));
    }

    @Test
    public void testSearchWithTitleEmptyCalendar() {
        ArrayList<Event> emptyResult = new ArrayList<Event>();
        assertEquals(emptyResult, c1.searchEvents("foo"));
    }

    @Test
    public void testSearchWithTitleNoResult() {
        ArrayList<Event> emptyResult = new ArrayList<Event>();
        assertEquals(emptyResult, c2.searchEvents("Nonexistent title"));
    }

    @Test
    public void testSearchWithTitleSingle() {
        ArrayList<Event> result = c2.searchEvents("goo");
        assertEquals(1, result.size());
        assertEquals(e3, result.get(0));
    }

    @Test
    public void testSearchWithTitleMultipleResults() {
        ArrayList<Event> result = c2.searchEvents("foo");
        assertEquals(2, result.size());
        assertEquals(e2, result.get(0));
        assertEquals(e4, result.get(1));
    }

    @Test
    public void testSetTitle() {
        c1.setTitle("This is a new title.");
        assertEquals("This is a new title.", c1.getTitle());
    }

}