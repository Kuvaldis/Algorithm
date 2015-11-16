package kuvaldis.algorithm.interview;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task92Test {

    @Test
    public void testSearch() throws Exception {
        final Task92 t = new Task92();
        final int[] a = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        assertEquals(0, t.search(a, 15));
        assertEquals(1, t.search(a, 16));
        assertEquals(2, t.search(a, 19));
        assertEquals(3, t.search(a, 20));
        assertEquals(4, t.search(a, 25));
        assertEquals(5, t.search(a, 1));
        assertEquals(6, t.search(a, 3));
        assertEquals(7, t.search(a, 4));
        assertEquals(8, t.search(a, 5));
        assertEquals(9, t.search(a, 7));
        assertEquals(10, t.search(a, 10));
        assertEquals(11, t.search(a, 14));
        assertEquals(-1, t.search(a, 100500));
        assertEquals(5, t.search(new int[] {2, 2, 2, 2, 2, 3, 2, 2}, 3));
    }
}