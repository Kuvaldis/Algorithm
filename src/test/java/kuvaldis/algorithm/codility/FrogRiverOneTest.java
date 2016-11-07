package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FrogRiverOneTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(6, new FrogRiverOne().solution(5, new int[]{1, 3, 1, 4, 2, 3, 5, 4}));
    }
}