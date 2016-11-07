package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountFactorsTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(8, new CountFactors().solution(24));
    }
}