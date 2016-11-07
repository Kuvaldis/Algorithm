package kuvaldis.algorithm.structure;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashtableTest {

    private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

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
        hashtable.put("b", "B");
        assertEquals("B", hashtable.get("b"));
        assertEquals(2, hashtable.size());
        hashtable.put("c", "C");
        assertEquals("C", hashtable.get("c"));
        assertEquals(3, hashtable.size());
        hashtable.remove("d");
        assertEquals(3, hashtable.size());
        hashtable.remove("c");
        assertNull(hashtable.get("c"));
        assertEquals(2, hashtable.size());
        hashtable.remove("b");
        assertNull(hashtable.get("b"));
        assertEquals(1, hashtable.size());
        hashtable.remove("a");
        assertNull(hashtable.get("a"));
        assertEquals(0, hashtable.size());

        // just for fun
        for (int i = 0; i < 10000; i++) {
            final String key = generateString(10);
            System.out.println("Put " + i + " key " + key);
            hashtable.put(key, key.toUpperCase());
        }
        System.out.println(hashtable.size());

        for (int i = 0; i < hashtable.buckets.length; i++) {
            int counter = 0;
            Hashtable.Node node = hashtable.buckets[i];
            while (node != null) {
                counter++;
                node = node.next;
            }
            System.out.println("bucket[" + i + "] count: " + counter);
        }
    }

    private String generateString(final int length) {
        final char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = alphabet[RandomUtils.nextInt(25)];
        }
        return new String(chars);
    }
}