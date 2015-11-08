package kuvaldis.algorithm.interview.arraysstrings;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Task22Test {

    @Test
    public void testFindFromLast() throws Exception {
        final Task22 t = new Task22();
        assertEquals(3, t.findFromLast(Node.fromList(Arrays.asList(1, 2, 3, 4, 5)), 2).val.intValue());
        assertEquals(5, t.findFromLast(Node.fromList(Arrays.asList(1, 2, 3, 4, 5)), 0).val.intValue());
        assertEquals(1, t.findFromLast(Node.fromList(Arrays.asList(1, 2, 3, 4, 5)), 4).val.intValue());
        assertNull(t.findFromLast(Node.fromList(Arrays.asList(1, 2, 3, 4, 5)), 5));
    }
}