package kuvaldis.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinimumCostOfTicketsTest {

    @Test
    public void testExample1() {
        // given
        final int[] days = new int[]{1, 4, 6, 7, 8, 20};
        final int[] costs = new int[]{2, 7, 15};

        // when
        final int result = new MinimumCostOfTickets().minCost(days, costs);

        // then
        assertEquals(11, result);
    }

    @Test
    public void testExample2() {
        // given
        final int[] days = new int[]{1,2,3,4,5,6,7,8,9,10,30,31};
        final int[] costs = new int[]{2, 7, 15};

        // when
        final int result = new MinimumCostOfTickets().minCost(days, costs);

        // then
        assertEquals(17, result);
    }
}