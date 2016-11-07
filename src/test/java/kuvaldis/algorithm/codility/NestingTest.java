package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.*;

public class NestingTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(1, new Nesting().solution("(()(())())"));
        assertEquals(0, new Nesting().solution("())"));
    }
}