package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberOfDiscIntersectionsTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(11, new NumberOfDiscIntersections().solution(new int[]{1, 5, 2, 1, 4, 0}));
    }
}