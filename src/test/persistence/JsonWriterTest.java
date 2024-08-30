package persistence;

import model.Calendar;
import model.Event;
import model.Time;
import model.Workroom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Workroom wr = new Workroom("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Workroom wr = new Workroom("My work room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            wr = reader.read();
            assertEquals("My work room", wr.getName());
            assertEquals(0, wr.numCalendars());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Workroom wr = new Workroom("My work room");
            wr.addCalendar(new Calendar());
            Calendar addedCalendar = new Calendar();
            addedCalendar.setTitle("added calendar");
            addedCalendar.getEvents().add(new Event(
                    new Time(1,2,3,4,5),
                    new Time(6,7,8,9,10)
            ));
            wr.addCalendar(addedCalendar);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            wr = reader.read();
            assertEquals("My work room", wr.getName());
            List<Calendar> calendars = wr.getCalendars();
            assertEquals(2, calendars.size());
            Calendar firstCalendar = calendars.get(0);
            Calendar secondCalendar = calendars.get(1);
            testAddedCalendars(firstCalendar, secondCalendar);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private void testAddedCalendars(Calendar firstCalendar, Calendar secondCalendar) {
        checkCalendar(firstCalendar, "New Calendar");
        assertEquals(0, firstCalendar.getEvents().size());
        checkCalendar(secondCalendar, "added calendar");
        List<Event> eventsInSecond = secondCalendar.getEvents();
        assertEquals(1, eventsInSecond.size());
        Event firstEvent = eventsInSecond.get(0);
        checkEvent(firstEvent, "New Event", "No description.", 0);
        checkTime(firstEvent.getStartTime(), 1, 2, 3, 4, 5);
        checkTime(firstEvent.getEndTime(), 6, 7, 8, 9, 10);
    }
}

