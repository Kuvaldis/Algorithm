package kuvaldis.algorithm.interview.arraysstrings;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task16Test {

    @Test
    public void testRotate() throws Exception {
        final Task16 t = new Task16();
        assertNull(t.rotate(null));
        assertArrayEquals(new int[][]{{1}}, t.rotate(new int[][]{{1}}));
        assertArrayEquals(new int[][]{{2, 4}, {1, 3}}, t.rotate(new int[][]{{1, 2}, {3, 4}}));
        assertArrayEquals(new int[][]{{3, 6}, {2, 5}, {1, 4}}, t.rotate(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }
}