package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayInversionCountTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(4, new ArrayInversionCount().solution(new int[]{-1, 6, 3, 4, 7, 4}));
    }
}