package kuvaldis.algorithm.cci;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortedSearchNoSizeTest {

    @Test
    public void simpleTest() {
        final int[] arr = new int[] {1, 4, 7, 10, 12, 13, 24, 25};
        final SortedSearchNoSize.Listy listy = new SortedSearchNoSize.Listy(arr);
        assertEquals(0, new SortedSearchNoSize().getIndex(listy, 1));
        assertEquals(1, new SortedSearchNoSize().getIndex(listy, 4));
        assertEquals(2, new SortedSearchNoSize().getIndex(listy, 7));
        assertEquals(3, new SortedSearchNoSize().getIndex(listy, 10));
        assertEquals(4, new SortedSearchNoSize().getIndex(listy, 12));
        assertEquals(5, new SortedSearchNoSize().getIndex(listy, 13));
        assertEquals(6, new SortedSearchNoSize().getIndex(listy, 24));
        assertEquals(7, new SortedSearchNoSize().getIndex(listy, 25));
    }
}