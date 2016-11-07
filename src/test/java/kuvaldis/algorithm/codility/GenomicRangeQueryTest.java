package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class GenomicRangeQueryTest {

    @Test
    public void testSolution() throws Exception {
        assertArrayEquals(new int[]{2, 4, 1},
                new GenomicRangeQuery().solution("CAGCCTA", new int[]{2, 5, 0}, new int[]{4, 5, 6}));
    }
}