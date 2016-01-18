package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountDistinctSlicesTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(9, new CountDistinctSlices().solution(6, new int[]{3, 4, 5, 5, 2}));
    }
}