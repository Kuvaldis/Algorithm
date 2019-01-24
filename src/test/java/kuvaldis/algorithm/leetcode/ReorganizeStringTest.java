package kuvaldis.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReorganizeStringTest {

    @Test
    public void simpleTest() {
        final ReorganizeString rs = new ReorganizeString();
        assertNotEquals("", rs.reorganizeString("abc"));
        assertNotEquals("", rs.reorganizeString("aab"));
        assertNotEquals("", rs.reorganizeString("aabbcc"));
        assertNotEquals("", rs.reorganizeString("aaaabbbccd"));

        assertEquals("", rs.reorganizeString("aaab"));
    }
}