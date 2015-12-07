package kuvaldis.algorithm.structure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BTreeSetTest {

    @Test
    public void testBTree() throws Exception {
        final BTreeSet set = new BTreeSet();
        assertEquals(0, set.height());
        assertEquals(0, set.size());

        set.add(1);
        assertTrue(set.contains(1));
        assertEquals(1, set.height());
        assertEquals(1, set.size());

        set.add(18);
        assertTrue(set.contains(18));
        assertEquals(1, set.height());
        assertEquals(2, set.size());

        set.add(36);
        assertTrue(set.contains(36));
        assertEquals(1, set.height());
        assertEquals(3, set.size());

        set.add(11);
        assertTrue(set.contains(11));
        assertEquals(1, set.height());
        assertEquals(4, set.size());

        set.add(15);
        assertTrue(set.contains(15));
        assertEquals(2, set.height());
        assertEquals(5, set.size());

        set.add(2);
        assertTrue(set.contains(2));
        assertEquals(2, set.height());
        assertEquals(6, set.size());

        set.add(38);
        assertTrue(set.contains(38));
        assertEquals(2, set.height());
        assertEquals(7, set.size());

        set.add(40);
        assertTrue(set.contains(40));
        assertEquals(2, set.height());
        assertEquals(8, set.size());

        set.add(41);
        assertTrue(set.contains(41));
        assertEquals(2, set.height());
        assertEquals(9, set.size());

        set.delete(1);
        assertFalse(set.contains(1));
        assertEquals(2, set.height());
        assertEquals(8, set.size());

        set.delete(15);
        assertFalse(set.contains(15));
        assertEquals(2, set.height());
        assertEquals(7, set.size());

        set.delete(38);
        assertFalse(set.contains(38));
        assertEquals(2, set.height());
        assertEquals(6, set.size());

        set.delete(40);
        assertFalse(set.contains(40));
        assertEquals(2, set.height());
        assertEquals(5, set.size());

        set.delete(18);
        assertFalse(set.contains(18));
        assertEquals(1, set.height());
        assertEquals(4, set.size());

        set.delete(36);
        assertFalse(set.contains(36));
        assertEquals(1, set.height());
        assertEquals(3, set.size());

        set.delete(11);
        assertFalse(set.contains(11));
        assertEquals(1, set.height());
        assertEquals(2, set.size());

        set.delete(2);
        assertFalse(set.contains(2));
        assertEquals(1, set.height());
        assertEquals(1, set.size());

        set.delete(41);
        assertFalse(set.contains(41));
        assertEquals(0, set.height());
        assertEquals(0, set.size());
    }
}