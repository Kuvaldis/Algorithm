package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxDoubleSliceSumTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(17, new MaxDoubleSliceSum().solution(new int[]{3, 2, 6, -1, 4, 5, -1, 2}));
    }
}