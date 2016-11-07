package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinMaxDivisionTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(6, new MinMaxDivision().solution(3, 5, new int[]{2, 1, 5, 1, 2, 2, 2}));
    }
}