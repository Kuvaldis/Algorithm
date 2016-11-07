package kuvaldis.algorithm.careercup;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContigiousSubarraysSumRangeTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(4, new ContigiousSubarraysSumRange().solution(new int[]{1, 2, 3}, 0, 3));
        assertEquals(3, new ContigiousSubarraysSumRange().solution(new int[]{-2, 5, -1}, -2, 2));
        assertEquals(6, new ContigiousSubarraysSumRange().solution(new int[]{5, 2, 4, 7}, 0, 10));
        assertEquals(17, new ContigiousSubarraysSumRange().solution(new int[]{1, 2, 1, 0, 0, 0}, 0, 3));
    }
}