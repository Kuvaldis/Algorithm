package kuvaldis.algorithm.cake;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Task4Test {

    @Test
    public void testSolution() throws Exception {
        // 1. 1, 1, 1, 1
        // 2. 2, 1, 1
        // 3. 2, 2
        // 4. 3, 1
        assertEquals(4, new Task5().solution(4, new int[]{1, 2, 3}));
        // 1. 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
        // 2. 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
        // 3. 2, 2, 1, 1, 1, 1, 1, 1, 1, 1
        // 4. 2, 2, 2, 1, 1, 1, 1, 1, 1
        // 5. 2, 2, 2, 2, 1, 1, 1, 1
        // 6. 2, 2, 2, 2, 2, 1, 1
        // 7. 2, 2, 2, 2, 2, 2
        // 8. 5, 1, 1, 1, 1, 1, 1, 1
        // 9. 5, 2, 1, 1, 1, 1, 1
        // 10. 5, 2, 2, 1, 1, 1
        // 11. 5, 2, 2, 2, 1
        // 12. 5, 5, 1, 1
        // 13. 5, 5, 2
        assertEquals(13, new Task5().solution(12, new int[]{1, 2, 5}));
        // 1. 2, 2, 2, 2, 2, 2
        // 2. 5, 5, 2
        assertEquals(2, new Task5().solution(12, new int[]{2, 5}));
    }
}