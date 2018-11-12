package kuvaldis.algorithm.programcreek;

import org.junit.Test;

import static org.junit.Assert.*;

public class HouseRobberTest {

    @Test
    public void testSimple() {
        final int[] houses = new int[] {10, 15, 2, 25, 11, 37, 5};
        assertEquals(77, new HouseRobber().maxSum(houses));
    }
}