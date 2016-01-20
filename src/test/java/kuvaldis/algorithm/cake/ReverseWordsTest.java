package kuvaldis.algorithm.cake;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseWordsTest {

    @Test
    public void testReverseWords() throws Exception {
        final ReverseWords r = new ReverseWords();
        assertNull(r.reverseWords(null));
        assertEquals("", r.reverseWords(""));
        assertEquals(" ", r.reverseWords(" "));
        assertEquals("  ", r.reverseWords("  "));
        assertEquals("   ", r.reverseWords("   "));
        assertEquals("a", r.reverseWords("a"));
        assertEquals("ab", r.reverseWords("ab"));
        assertEquals("abc", r.reverseWords("abc"));
        assertEquals("a b", r.reverseWords("b a"));
        assertEquals("ab cd", r.reverseWords("cd ab"));
        assertEquals("ab cd abcd", r.reverseWords("abcd cd ab"));
        assertEquals("ab cd  abcd", r.reverseWords("abcd  cd ab"));
        assertEquals("  ab cd abcd", r.reverseWords("abcd cd ab  "));
        assertEquals("  ab cd abcd ", r.reverseWords(" abcd cd ab  "));
        assertEquals("find you will pain only go you recordings security the into if",
                r.reverseWords("if into the security recordings you go only pain will you find"));
    }
}