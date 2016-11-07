package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EquiLeaderTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(2, new EquiLeader().solution(new int[]{4, 3, 4, 4, 4, 2}));
    }
}