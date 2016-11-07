package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PeaksTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(3, new Peaks().solution(new int[]{1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2}));
    }
}