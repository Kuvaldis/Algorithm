package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommonPrimeDivisorsTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(1, new CommonPrimeDivisors().solution(new int[]{15, 10, 3}, new int[]{75, 30, 5}));
    }
}