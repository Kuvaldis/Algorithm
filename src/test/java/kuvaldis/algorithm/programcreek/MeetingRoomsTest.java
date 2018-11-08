package kuvaldis.algorithm.programcreek;

import kuvaldis.algorithm.programcreek.MeetingRooms.Interval;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MeetingRoomsTest {

    @Test
    public void testSimple() {
        final List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(0, 10));
        intervals.add(new Interval(20, 30));
        intervals.add(new Interval(5, 15));
        intervals.add(new Interval(15, 25));
        intervals.add(new Interval(5, 20));
        assertEquals(3, new MeetingRooms().calculate(intervals));
    }
}