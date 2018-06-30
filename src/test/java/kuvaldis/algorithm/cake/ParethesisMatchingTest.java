package kuvaldis.algorithm.cake;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParethesisMatchingTest {

    @Test
    public void simpleTest() {
        // given
        final String s = "Sometimes (when I nest them (my parentheticals) too much (like this (and this))) they get confusing.";

        // when
        int result1 = new ParethesisMatching().findMatch(s, 10);
        int result2 = new ParethesisMatching().findMatch(s, 28);
        int result3 = new ParethesisMatching().findMatch(s, 57);
        int result4 = new ParethesisMatching().findMatch(s, 68);

        // then
        assertEquals(79, result1);
        assertEquals(46, result2);
        assertEquals(78, result3);
        assertEquals(77, result4);
    }
}