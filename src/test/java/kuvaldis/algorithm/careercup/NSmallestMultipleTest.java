package kuvaldis.algorithm.careercup;

import org.junit.Test;

import static org.junit.Assert.*;

public class NSmallestMultipleTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(18, new NSmallestMultiple().solution(new int[]{4, 6}, 6));
        assertEquals(20, new NSmallestMultiple().solution(new int[]{4, 6}, 7));
        assertEquals(6, new NSmallestMultiple().solution(new int[]{3, 5, 11}, 3));
        assertEquals(9, new NSmallestMultiple().solution(new int[]{3, 5, 11}, 4));
        assertEquals(11, new NSmallestMultiple().solution(new int[]{3, 5, 11}, 6));
        assertEquals(12, new NSmallestMultiple().solution(new int[]{3, 5, 11}, 7));
    }
}