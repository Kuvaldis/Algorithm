package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.*;

public class StrSymmetryPointTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(3, new StrSymmetryPoint().solution("racecar"));
        assertEquals(0, new StrSymmetryPoint().solution("x"));
    }
}