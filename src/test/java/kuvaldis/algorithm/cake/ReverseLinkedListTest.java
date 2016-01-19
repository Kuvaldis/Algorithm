package kuvaldis.algorithm.cake;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseLinkedListTest {

    @Test
    public void testReverse() throws Exception {
        final ReverseLinkedList.LinkedListNode first = new ReverseLinkedList.LinkedListNode(1);
        final ReverseLinkedList.LinkedListNode second = new ReverseLinkedList.LinkedListNode(2);
        final ReverseLinkedList.LinkedListNode third = new ReverseLinkedList.LinkedListNode(3);
        final ReverseLinkedList.LinkedListNode fourth = new ReverseLinkedList.LinkedListNode(4);
        final ReverseLinkedList.LinkedListNode fifth = new ReverseLinkedList.LinkedListNode(5);

        assertNull(new ReverseLinkedList().reverse(null));

        ReverseLinkedList.LinkedListNode r = new ReverseLinkedList().reverse(first);
        assertEquals(1, r.value);

        first.next = second;
        r = new ReverseLinkedList().reverse(first);
        assertEquals(2, r.value);
        assertEquals(1, r.next.value);
        assertNull(r.next.next);

        first.next = second;
        second.next = third;
        r = new ReverseLinkedList().reverse(first);
        assertEquals(3, r.value);
        assertEquals(2, r.next.value);
        assertEquals(1, r.next.next.value);
        assertNull(r.next.next.next);

        first.next = second;
        second.next = third;
        third.next = fourth;
        r = new ReverseLinkedList().reverse(first);
        assertEquals(4, r.value);
        assertEquals(3, r.next.value);
        assertEquals(2, r.next.next.value);
        assertEquals(1, r.next.next.next.value);
        assertNull(r.next.next.next.next);

        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        r = new ReverseLinkedList().reverse(first);
        assertEquals(5, r.value);
        assertEquals(4, r.next.value);
        assertEquals(3, r.next.next.value);
        assertEquals(2, r.next.next.next.value);
        assertEquals(1, r.next.next.next.next.value);
        assertNull(r.next.next.next.next.next);
    }
}