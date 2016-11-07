package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxSliceSumTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(5, new MaxSliceSum().solution(new int[]{3, 2, -6, 4, 0}));
    }
}