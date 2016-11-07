package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StoneWallTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(7, new StoneWall().solution(new int[]{8, 8, 5, 7, 9, 8, 7, 4, 8}));
    }
}