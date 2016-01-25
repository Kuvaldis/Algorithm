package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxProfitTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(356, new MaxProfit().solution(new int[]{23171, 21011, 21123, 21366, 21013, 21367}));
    }
}