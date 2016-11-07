package kuvaldis.algorithm.dynamic;

import org.junit.Test;

import static org.junit.Assert.*;

public class DuffelBagMaxProfitTest {

    @Test
    public void testMaxProfit() throws Exception {
        assertEquals(555, new DuffelBag().maxProfit(new int[]{7, 3, 2}, new int[]{160, 90, 15}, 20));
    }
}