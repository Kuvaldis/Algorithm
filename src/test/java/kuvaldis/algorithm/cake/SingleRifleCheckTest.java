package kuvaldis.algorithm.cake;

import org.junit.Test;

import static org.junit.Assert.*;

public class SingleRifleCheckTest {

    @Test
    public void testIsSingleRifle() throws Exception {
        final SingleRifleCheck c = new SingleRifleCheck();
        assertTrue(c.isSingleRifle(new int[]{1, 5, 6, 2, 3, 7, 4, 8}, new int[]{1, 2, 3, 4}, new int[]{5, 6, 7, 8}));
        assertTrue(c.isSingleRifle(new int[]{1, 4, 2, 5, 3, 6, 7, 8}, new int[]{1, 2, 3}, new int[]{4, 5, 6, 7, 8}));
        assertTrue(c.isSingleRifle(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{}, new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
        assertFalse(c.isSingleRifle(new int[]{5, 1, 2, 3, 7, 4, 6, 8}, new int[]{1, 2, 3, 4}, new int[]{5, 6, 7, 8}));
        assertTrue(c.isSingleRifle(new int[]{5, 1, 2, 3, 7, 4, 6, 8}, new int[]{1, 2, 3, 4}, new int[]{5, 7, 6, 8}));
        assertTrue(c.isSingleRifle(new int[]{4, 1, 3, 8, 1, 7, 9, 5}, new int[]{1, 3, 7, 9}, new int[]{4, 8, 1, 5}));
    }
}