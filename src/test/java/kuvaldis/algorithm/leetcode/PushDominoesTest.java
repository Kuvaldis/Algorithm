package kuvaldis.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class PushDominoesTest {

    @Test
    public void testExample1() {
        // given
        final char[] dominoes = ".L.R...LR..L..".toCharArray();

        // when
        final char[] result = new PushDominoes().push(dominoes);

        // then
        assertEquals("LL.RR.LLRRLL..", new String(result));
    }

    @Test
    public void testExample2() {
        // given
        final char[] dominoes = "RR.L".toCharArray();

        // when
        final char[] result = new PushDominoes().push(dominoes);

        // then
        assertEquals("RR.L", new String(result));
    }

    @Test
    public void testExample3() {
        // given
        final char[] dominoes = "R...R..L..L".toCharArray();

        // when
        final char[] result = new PushDominoes().push(dominoes);

        // then
        assertEquals("RRRRRRLLLLL", new String(result));
    }
}