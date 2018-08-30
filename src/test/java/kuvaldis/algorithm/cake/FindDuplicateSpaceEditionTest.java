package kuvaldis.algorithm.cake;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FindDuplicateSpaceEditionTest {

    @Test
    public void testFindDuplicate() {
        FindDuplicateSpaceEdition find = new FindDuplicateSpaceEdition();

        assertEquals(1, find.find(new int[]{1, 1}));
        assertEquals(1, find.find(new int[]{1, 1, 1}));
        assertEquals(1, find.find(new int[]{1, 1, 1, 1}));
        assertEquals(1, find.find(new int[]{1, 1, 1, 1, 1}));

        assertEquals(1, find.find(new int[]{1, 1, 2}));
        assertEquals(1, find.find(new int[]{1, 2, 1}));
        assertEquals(1, find.find(new int[]{2, 1, 1}));
        assertEquals(2, find.find(new int[]{2, 2, 1}));
        assertEquals(2, find.find(new int[]{2, 1, 2}));
        assertEquals(2, find.find(new int[]{1, 2, 2}));

        assertEquals(1, find.find(new int[]{1, 2, 3, 1}));
        assertEquals(2, find.find(new int[]{2, 3, 1, 2}));
        assertEquals(3, find.find(new int[]{3, 2, 3, 1}));

        assertEquals(1, find.find(new int[]{1, 2, 1, 4, 1}));

        assertTrue(Arrays.asList(1, 4).contains(find.find(new int[]{1, 4, 1, 4, 1})));
    }
}