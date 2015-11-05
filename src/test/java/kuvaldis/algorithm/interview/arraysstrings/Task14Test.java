package kuvaldis.algorithm.interview.arraysstrings;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task14Test {

    @Test
    public void testAreAnagrams() throws Exception {
        final Task14 t = new Task14();
        assertFalse(t.areAnagrams(null, null));
        assertTrue(t.areAnagrams("", ""));
        assertTrue(t.areAnagrams("abc", "cba"));
        assertFalse(t.areAnagrams("abcc", "cba"));
        assertTrue(t.areAnagrams("abcc", "cbca"));
        assertFalse(t.areAnagrams("ab", "a"));
    }
}