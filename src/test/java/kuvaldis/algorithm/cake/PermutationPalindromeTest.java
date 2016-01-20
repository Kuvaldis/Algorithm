package kuvaldis.algorithm.cake;

import org.junit.Test;

import static org.junit.Assert.*;

public class PermutationPalindromeTest {

    @Test
    public void testIsPermPalindrome() throws Exception {
        final PermutationPalindrome p = new PermutationPalindrome();
        assertTrue(p.isPermPalindrome("civic"));
        assertTrue(p.isPermPalindrome("ivicc"));
        assertFalse(p.isPermPalindrome("civil"));
        assertFalse(p.isPermPalindrome("livci"));
        assertFalse(p.isPermPalindrome("ccviii"));
    }
}