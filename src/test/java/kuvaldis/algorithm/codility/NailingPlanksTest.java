package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NailingPlanksTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(4, new NailingPlanks().solution(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 7, 10, 2}));
    }
}