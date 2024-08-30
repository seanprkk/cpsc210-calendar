package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {

    private Time t1, t2, t3, t4, t5, t6;

    @BeforeEach
    public void setup() {

        t1 = new Time(2022, 6, 6, 13, 20);
        t2 = new Time(2022, 12, 31, 23, 59);
        t3 = new Time (2023, 4, 15, 6, 35);
        t4 = new Time (2023, 4, 17, 6, 35);
        t5 = new Time (2023, 4, 17, 19, 35);
        t6 = new Time (2023, 4, 17, 19, 40);
    }

    @Test
    public void testConstructor() {
        assertEquals(2022, t1.getYear());
        assertEquals(6, t1.getMonth());
        assertEquals(6, t1.getDay());
        assertEquals(13, t1.getHour());
        assertEquals(20, t1.getMinute());
        assertEquals(202206061320L, t1.getNumRep());
        assertEquals("13:20, 6/6/2022", t1.getStringRep());

        assertEquals(2022, t2.getYear());
        assertEquals(12, t2.getMonth());
        assertEquals(31, t2.getDay());
        assertEquals(23, t2.getHour());
        assertEquals(59, t2.getMinute());
        assertEquals(202212312359L, t2.getNumRep());
        assertEquals("23:59, 12/31/2022", t2.getStringRep());

        assertEquals(2023, t5.getYear());
        assertEquals(4, t5.getMonth());
        assertEquals(17, t5.getDay());
        assertEquals(19, t5.getHour());
        assertEquals(35, t5.getMinute());
        assertEquals(202304171935L, t5.getNumRep());
        assertEquals("19:35, 4/17/2023", t5.getStringRep());
    }

    @Test
    public void testIsBeforeTrue() {
        assertTrue(t1.isBeforeOrEqualTo(t2));
        assertTrue(t2.isBeforeOrEqualTo(t3));
        assertTrue(t3.isBeforeOrEqualTo(t4));
        assertTrue(t4.isBeforeOrEqualTo(t5));
        assertTrue(t5.isBeforeOrEqualTo(t6));
    }

    @Test
    public void testIsBeforeFalse() {
        assertFalse(t6.isBeforeOrEqualTo(t5));
        assertFalse(t5.isBeforeOrEqualTo(t4));
        assertFalse(t4.isBeforeOrEqualTo(t3));
        assertFalse(t3.isBeforeOrEqualTo(t2));
        assertFalse(t2.isBeforeOrEqualTo(t1));
    }

    @Test
    public void testSetMonth() {
        t1.setMonth(18);
        assertEquals(18, t1.getMonth());
        assertEquals("13:20, 18/6/2022", t1.getStringRep());
        assertEquals(202218061320L, t1.getNumRep());
    }

    @Test
    public void testSetYear() {
        t1.setYear(18);
        assertEquals(18, t1.getYear());
        assertEquals("13:20, 6/6/18", t1.getStringRep());
        assertEquals(1806061320L, t1.getNumRep());
    }

    @Test
    public void testSetDay() {
        t1.setDay(18);
        assertEquals(18, t1.getDay());
        assertEquals("13:20, 6/18/2022", t1.getStringRep());
        assertEquals(202206181320L, t1.getNumRep());
    }

    @Test
    public void testSetHour() {
        t1.setHour(18);
        assertEquals(18, t1.getHour());
        assertEquals("18:20, 6/6/2022", t1.getStringRep());
        assertEquals(202206061820L, t1.getNumRep());
    }

    @Test
    public void testSetMinute() {
        t1.setMinute(18);
        assertEquals(18, t1.getMinute());
        assertEquals("13:18, 6/6/2022", t1.getStringRep());
        assertEquals(202206061318L, t1.getNumRep());
    }

}
