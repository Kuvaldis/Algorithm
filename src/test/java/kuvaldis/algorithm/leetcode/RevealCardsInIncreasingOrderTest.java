package kuvaldis.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class RevealCardsInIncreasingOrderTest {

    @Test
    public void testExample1() {
        // given
        final int[] deck = new int[]{17, 13, 11, 2, 3, 5, 7};

        // when
        final int[] order = new RevealCardsInIncreasingOrder().order(deck);

        // then
        assertArrayEquals(new int[]{2, 13, 3, 11, 5, 17, 7}, order);
    }

    @Test
    public void testExample2() {
        // given
        final int[] deck = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 40, 57, 60};

        // when
        final int[] order = new RevealCardsInIncreasingOrder().order(deck);

        // then
        assertArrayEquals(new int[]{2, 17, 3, 40, 5, 19, 7, 60, 11, 23, 13, 57}, order);
    }
}