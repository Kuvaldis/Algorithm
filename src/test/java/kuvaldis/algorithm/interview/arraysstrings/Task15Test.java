package kuvaldis.algorithm.interview.arraysstrings;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task15Test {

    @Test
    public void testReplace() throws Exception {
        final Task15 t = new Task15();
        assertEquals(null, t.replace(null));
        assertEquals("", t.replace(""));
        assertEquals("%20", t.replace(" "));
        assertEquals("a", t.replace("a"));
        assertEquals("%20", t.replace("%20"));
        assertEquals("a%20b", t.replace("a b"));
    }
}