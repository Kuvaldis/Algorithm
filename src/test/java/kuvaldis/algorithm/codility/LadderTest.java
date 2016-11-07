package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class LadderTest {

    @Test
    public void testSolution() throws Exception {
        assertArrayEquals(new int[]{5, 1, 8, 0, 1}, new Ladder().solution(new int[]{4, 4, 5, 5, 1}, new int[]{3, 2, 4, 3, 1}));
    }
}