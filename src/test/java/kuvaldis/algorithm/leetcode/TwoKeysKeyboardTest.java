package kuvaldis.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class TwoKeysKeyboardTest {

    @Test
    public void simpleTest() {
        assertEquals(0, new TwoKeysKeyboard().minimalSteps(1));
        assertEquals(2, new TwoKeysKeyboard().minimalSteps(2));
        assertEquals(3, new TwoKeysKeyboard().minimalSteps(3));
        assertEquals(4, new TwoKeysKeyboard().minimalSteps(4));
        assertEquals(5, new TwoKeysKeyboard().minimalSteps(5));
        assertEquals(5, new TwoKeysKeyboard().minimalSteps(6));
        assertEquals(7, new TwoKeysKeyboard().minimalSteps(7));
        assertEquals(24, new TwoKeysKeyboard().minimalSteps(143));
    }
}