package kuvaldis.algorithm.interview.arraysstrings;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task11Test {
    @Test
    public void testCharUnique() throws Exception {
        assertTrue(new Task11().charUnique("a"));
        assertTrue(new Task11().charUnique("abc"));
        assertTrue(new Task11().charUnique(""));
        assertTrue(new Task11().charUnique(null));
        assertFalse(new Task11().charUnique("aa"));
        assertFalse(new Task11().charUnique("abca"));
    }
}