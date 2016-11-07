package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.*;

public class FrogJmpTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(3, new FrogJmp().solution(10, 85, 30));
    }
}