package kuvaldis.algorithm.programcreek;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrappingRainWaterTest {

    @Test
    public void simpleTest() {
        assertEquals(6, new TrappingRainWater().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        assertEquals(4, new TrappingRainWater().trap(new int[]{3, 2, 1, 2, 4}));
    }
}