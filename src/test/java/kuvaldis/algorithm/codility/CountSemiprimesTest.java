package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CountSemiprimesTest {

    @Test
    public void testSolution() throws Exception {
        assertArrayEquals(new int[]{10, 4, 0}, new CountSemiprimes().solution(26, new int[]{1, 4, 16}, new int[]{26, 10, 20}));
    }
}