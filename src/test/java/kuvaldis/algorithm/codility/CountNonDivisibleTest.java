package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class CountNonDivisibleTest {

    @Test
    public void testSolution() throws Exception {
        assertArrayEquals(new int[]{2, 4, 3, 2, 0}, new CountNonDivisible().solution(new int[]{3, 1, 2, 3, 6}));
    }
}