package kuvaldis.algorithm.programcreek;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinimumSizeSubarraySumTest {

    @Test
    public void testSimple() {
        final int[] arr = new int[] {2, 3, 1, 2, 4, 3};
        final int s = 7;
        final int result = new MinimumSizeSubarraySum().findLength(arr, s);
        assertEquals(2, result);
    }
}