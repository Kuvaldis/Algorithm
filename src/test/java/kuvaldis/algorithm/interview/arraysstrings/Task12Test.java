package kuvaldis.algorithm.interview.arraysstrings;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task12Test {

    @Test
    public void testRevert() throws Exception {
        assertArrayEquals(new char[]{'c', 'b', 'a', '\0'}, new Task12().revert(new char[]{'a', 'b', 'c', '\0'}));
        assertNull(new Task12().revert(null));
        assertArrayEquals(new char[]{'a', '\0'}, new Task12().revert(new char[]{'a', '\0'}));
    }
}