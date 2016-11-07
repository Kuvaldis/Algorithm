package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberSolitaireTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(8, new NumberSolitaire().solution(new int[]{1, -2, 0, 9, -1, -2}));
    }
}