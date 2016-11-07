package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinAbsSumTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(0, new MinAbsSum().solution(new int[]{1, 5, 2, -2}));
    }
}