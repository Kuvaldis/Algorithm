package kuvaldis.algorithm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TieRopesTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(3, new TieRopes().solution(4, new int[]{1, 2, 3, 4, 1, 1, 3}));
    }
}