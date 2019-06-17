package kuvaldis.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class KokoEatingBananasTest {

    @Test
    public void testExample1() {
        // given
        final int[] piles = new int[]{3, 6, 7, 11};
        final int h = 8;

        // when
        final int speed = new KokoEatingBananas().findSpeed(piles, h);

        // then
        assertEquals(4, speed);
    }

    @Test
    public void testExample2() {
        // given
        final int[] piles = new int[]{30, 11, 23, 4, 20};
        final int h = 5;

        // when
        final int speed = new KokoEatingBananas().findSpeed(piles, h);

        // then
        assertEquals(30, speed);
    }

    @Test
    public void testExample3() {
        // given
        final int[] piles = new int[]{30, 11, 23, 4, 20};
        final int h = 6;

        // when
        final int speed = new KokoEatingBananas().findSpeed(piles, h);

        // then
        assertEquals(23, speed);
    }
}