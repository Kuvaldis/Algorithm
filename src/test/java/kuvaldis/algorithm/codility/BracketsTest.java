package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.*;

public class BracketsTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(1, new Brackets().solution("{[()()]}"));
        assertEquals(0, new Brackets().solution("([)()]"));
    }
}