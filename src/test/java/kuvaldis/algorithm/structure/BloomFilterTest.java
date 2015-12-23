package kuvaldis.algorithm.structure;

import org.junit.Test;

import static org.junit.Assert.*;

public class BloomFilterTest {

    @Test
    public void testBloomFilter() throws Exception {
        final BloomFilter filter = new BloomFilter(64, 0.1f);
        filter.add("apple");
        filter.add("orange");
        filter.add("banana");
        // will always be true for sure
        assertTrue(filter.contains("apple"));
        assertTrue(filter.contains("orange"));
        assertTrue(filter.contains("banana"));

        // may be true, or false. But if false, then it definitely doesn't contain the item.
        // so there might be false positive results.
        System.out.println(filter.contains("cabbage"));
        System.out.println(filter.contains("cucumber"));
        System.out.println(filter.contains("sausage"));
        System.out.println(filter.contains("poop"));
    }
}