package kuvaldis.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class LongestSubstringWithoutRepeatingCharactersTest {

    @Test
    public void testSimple() {
        assertEquals(3, new LongestSubstringWithoutRepeatingCharacters().find("abcabcbb"));
        assertEquals(1, new LongestSubstringWithoutRepeatingCharacters().find("bbbbb"));
        assertEquals(3, new LongestSubstringWithoutRepeatingCharacters().find("pwwkew"));
    }
}