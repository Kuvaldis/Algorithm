package kuvaldis.algorithm.interview.arraysstrings;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task13Test {

    @Test
    public void testRemoveDuplicates() throws Exception {
        final Task13 t = new Task13();
        assertEquals(null, t.removeDuplicates(null));
        assertArrayEquals(new char[]{}, t.removeDuplicates(new char[]{}));
        assertArrayEquals("a".toCharArray(), t.removeDuplicates("a".toCharArray()));
        assertArrayEquals("a".toCharArray(), t.removeDuplicates("aa".toCharArray()));
        assertArrayEquals("a".toCharArray(), t.removeDuplicates("aaa".toCharArray()));
        assertArrayEquals("ab".toCharArray(), t.removeDuplicates("ab".toCharArray()));
        assertArrayEquals("ab".toCharArray(), t.removeDuplicates("aab".toCharArray()));
        assertArrayEquals("ab".toCharArray(), t.removeDuplicates("aabb".toCharArray()));
        assertArrayEquals("ab".toCharArray(), t.removeDuplicates("abab".toCharArray()));
        assertArrayEquals("abc".toCharArray(), t.removeDuplicates("abacb".toCharArray()));
    }
}