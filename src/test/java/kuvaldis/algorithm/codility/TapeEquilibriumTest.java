package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.*;

public class TapeEquilibriumTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(1, new TapeEquilibrium().solution(new int[] {3, 1, 2, 4, 3}));
    }
}