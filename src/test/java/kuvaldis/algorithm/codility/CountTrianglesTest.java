package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountTrianglesTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(4, new CountTriangles().solution(new int[]{10, 2, 5, 1, 8, 12}));
    }
}