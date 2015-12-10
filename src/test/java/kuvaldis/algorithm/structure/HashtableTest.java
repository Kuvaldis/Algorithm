package kuvaldis.algorithm.structure;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashtableTest {

    @Test
    public void testPut() throws Exception {
        final Hashtable hashtable = new Hashtable();
        assertNull(hashtable.get(null));
        assertNull(hashtable.get("a"));
        assertEquals(0, hashtable.size());
        hashtable.put("a", "B");
        assertEquals("B", hashtable.get("a"));
        assertEquals(1, hashtable.size());
        hashtable.put("a", "A");
        assertEquals("A", hashtable.get("a"));
        assertEquals(1, hashtable.size());
    }
}