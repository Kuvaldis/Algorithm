package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChocolatesByNumbersTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(5, new ChocolatesByNumbers().solution(10, 4));
    }
}