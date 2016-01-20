package kuvaldis.algorithm.cake;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WhichAppearsTwiceTest {

    @Test
    public void testSolution() throws Exception {
        final WhichAppearsTwice w = new WhichAppearsTwice();
        assertEquals(3, w.solution(new int[]{1, 2, 3, 3}));
        assertEquals(2, w.solution(new int[]{1, 2, 2, 3}));
        assertEquals(1, w.solution(new int[]{1, 1, 2, 3}));
        assertEquals(1, w.solution(new int[]{3, 11, 8, 4, 1, 10, 2, 7, 1, 5, 9, 6}));
    }
}