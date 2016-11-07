package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinAbsSumOfTwoTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(1, new MinAbsSumOfTwo().solution(new int[]{1, 4, -3}));
        assertEquals(3, new MinAbsSumOfTwo().solution(new int[]{-8, 4, 5, -10, 3}));
    }
}