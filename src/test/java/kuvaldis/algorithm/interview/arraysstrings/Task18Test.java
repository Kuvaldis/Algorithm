package kuvaldis.algorithm.interview.arraysstrings;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task18Test {

    @Test
    public void testIsRotation() throws Exception {
        final Task18 t = new Task18();
        assertTrue(t.isRotation("abc", "cab"));
        assertTrue(t.isRotation("waterbottle", "erbottlewat"));
    }
}