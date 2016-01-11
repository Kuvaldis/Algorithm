package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PermMissingElemTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(4, new PermMissingElem().solution(new int[]{2, 3, 1, 5}));
    }
}