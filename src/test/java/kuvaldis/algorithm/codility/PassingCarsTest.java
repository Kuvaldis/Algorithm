package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PassingCarsTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(5, new PassingCars().solution(new int[]{0, 1, 0, 1, 1}));
    }
}