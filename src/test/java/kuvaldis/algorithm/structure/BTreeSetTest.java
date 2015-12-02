package kuvaldis.algorithm.structure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BTreeSetTest {

    @Test
    public void testBTree() throws Exception {
        final BTreeSet set = new BTreeSet();
        assertEquals(0, set.height());
        assertEquals(0, set.size());
        set.add(1);
        assertEquals(1, set.height());
        assertEquals(1, set.size());
        set.add(18);
        assertEquals(1, set.height());
        assertEquals(2, set.size());
        set.add(36);
        assertEquals(1, set.height());
        assertEquals(3, set.size());
        set.add(11);
        assertEquals(1, set.height());
        assertEquals(4, set.size());
        set.add(15);
        assertEquals(2, set.height());
        assertEquals(5, set.size());
        set.add(2);
        assertEquals(2, set.height());
        assertEquals(6, set.size());
        set.add(38);
        assertEquals(2, set.height());
        assertEquals(7, set.size());
        set.add(40);
        assertEquals(2, set.height());
        assertEquals(8, set.size());
        set.add(41);
        assertEquals(2, set.height());
        assertEquals(9, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(11));
        assertTrue(set.contains(15));
        assertTrue(set.contains(18));
        assertTrue(set.contains(36));
        assertTrue(set.contains(38));
        assertTrue(set.contains(40));
        assertTrue(set.contains(41));
    }
}