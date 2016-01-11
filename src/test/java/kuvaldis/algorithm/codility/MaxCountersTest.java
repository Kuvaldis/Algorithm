package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MaxCountersTest {

    @Test
    public void testSolution() throws Exception {
        assertArrayEquals(new int[]{3, 2, 2, 4, 2}, new MaxCounters().solution(5, new int[]{3, 4, 4, 6, 1, 4, 4}));
    }
}