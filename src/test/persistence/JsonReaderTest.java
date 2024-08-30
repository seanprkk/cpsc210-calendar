package persistence;

import model.Calendar;
import model.Event;
import model.Workroom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Workroom wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            Workroom wr = reader.read();
            assertEquals("Workroom", wr.getName());
            assertEquals(0, wr.numCalendars());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            Workroom wr = reader.read();
            assertEquals("Workroom", wr.getName());
            List<Calendar> calendars = wr.getCalendars();
            assertEquals(2, calendars.size());
            Calendar firstCalendar = calendars.get(0);
            Calendar secondCalendar = calendars.get(1);
            checkCalendar(firstCalendar, "first");
            checkCalendar(secondCalendar, "second");
            List<Event> eventsInSecond = secondCalendar.getEvents();
            Event firstEvent = eventsInSecond.get(0);
            Event secondEvent = eventsInSecond.get(1);
            checkEvent(firstEvent, "first", "No description.", 0);
            checkEvent(secondEvent, "second", "Changed description.", 0);
            checkTime(firstEvent.getStartTime(), 1, 1, 1, 1, 1);
            checkTime(firstEvent.getEndTime(), 2, 2, 2, 2, 2);
            checkTime(secondEvent.getStartTime(), 2, 2, 2, 2, 1);
            checkTime(secondEvent.getEndTime(), 3, 3, 3, 3, 3);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
