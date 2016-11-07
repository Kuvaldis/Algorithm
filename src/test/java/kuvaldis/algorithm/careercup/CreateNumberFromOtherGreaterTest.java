package kuvaldis.algorithm.careercup;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreateNumberFromOtherGreaterTest {

    @Test
    public void testSolution() throws Exception {
        assertTrue(new CreateNumberFromOtherGreater().solution(5281, 7443) > 7443);
        assertTrue(new CreateNumberFromOtherGreater().solution(5271, 7443) > 7443);
        assertEquals(-1, new CreateNumberFromOtherGreater().solution(5261, 7443));
    }
}