package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxNonoverlappingSegmentsTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(3, new MaxNonoverlappingSegments().solution(new int[]{1, 3, 7, 9, 9}, new int[]{5, 6, 8, 9, 10}));
    }
}