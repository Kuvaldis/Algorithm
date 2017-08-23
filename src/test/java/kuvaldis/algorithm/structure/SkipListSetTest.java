package kuvaldis.algorithm.structure;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SkipListSetTest {

    @Test
    public void testAdd() throws Exception {
        final SkipListSet set = new SkipListSet();

        set.add(10);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(new int[] {10}, set.toArray());

        set.add(1);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(new int[] {1, 10}, set.toArray());

        set.add(5);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(new int[] {1, 5, 10}, set.toArray());

        set.add(-1);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(new int[] {-1, 1, 5, 10}, set.toArray());

        set.add(20);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(new int[] {-1, 1, 5, 10, 20}, set.toArray());

        set.add(3);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(new int[] {-1, 1, 3, 5, 10, 20}, set.toArray());

        set.add(3);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(new int[] {-1, 1, 3, 5, 10, 20}, set.toArray());

        set.add(12);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(new int[] {-1, 1, 3, 5, 10, 12, 20}, set.toArray());

        set.add(11);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(new int[] {-1, 1, 3, 5, 10, 11, 12, 20}, set.toArray());

        set.add(-5);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(new int[] {-5, -1, 1, 3, 5, 10, 11, 12, 20}, set.toArray());

        set.add(-4);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(new int[] {-5, -4, -1, 1, 3, 5, 10, 11, 12, 20}, set.toArray());

        set.add(13);
        System.out.println(Arrays.toString(set.toArray()));
        assertArrayEquals(new int[] {-5, -4, -1, 1, 3, 5, 10, 11, 12, 13, 20}, set.toArray());
    }
}