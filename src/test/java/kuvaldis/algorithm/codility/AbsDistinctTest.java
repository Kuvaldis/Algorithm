package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbsDistinctTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(5, new AbsDistinct().solution(new int[]{-5, -3, -1, 0, 3, 6}));
    }
}