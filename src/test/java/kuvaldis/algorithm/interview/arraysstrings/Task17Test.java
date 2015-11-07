package kuvaldis.algorithm.interview.arraysstrings;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task17Test {

    @Test
    public void testZeroErase() throws Exception {
        final Task17 t = new Task17();
        assertArrayEquals(new int[][]{{0, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}, {0, 2, 0, 0}},
                t.zeroErase(new int[][]{{1, 2, 0, 0}, {3, 1, 6, 2}, {0, 5, 0, 4}, {1, 2, 3, 1}}));
    }
}