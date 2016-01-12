package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxProductOfThreeTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(60, new MaxProductOfThree().solution(new int[]{-3, 1, 2, -2, 5, 6}));
    }
}