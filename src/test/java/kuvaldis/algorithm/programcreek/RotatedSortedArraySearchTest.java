package kuvaldis.algorithm.programcreek;

import org.junit.Test;

import static org.junit.Assert.*;

public class RotatedSortedArraySearchTest {

    @Test
    public void simpleTest() {
        final int[] arr = new int[] {4, 5, 6, 7, 0, 1, 2};
        assertEquals(1, new RotatedSortedArraySearch().search(arr, 5));
        assertEquals(-1, new RotatedSortedArraySearch().search(arr, 3));
        assertEquals(2, new RotatedSortedArraySearch().search(arr, 6));
        assertEquals(5, new RotatedSortedArraySearch().search(arr, 1));
        assertEquals(3, new RotatedSortedArraySearch().search(arr, 7));
    }
}