package kuvaldis.algorithm.programcreek;

import kuvaldis.algorithm.programcreek.OddEvenLinkedList.ListNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class OddEvenLinkedListTest {

    @Test
    public void testReorder1() {
        final ListNode head = OddEvenLinkedList.createConsequent(1);
        new OddEvenLinkedList().reorder(head);
        assertEquals(Collections.singletonList(1), head.toJavaList());
    }

    @Test
    public void testReorder2() {
        final ListNode head = OddEvenLinkedList.createConsequent(2);
        new OddEvenLinkedList().reorder(head);
        assertEquals(Arrays.asList(1, 2), head.toJavaList());
    }

    @Test
    public void testReorder3() {
        final ListNode head = OddEvenLinkedList.createConsequent(3);
        new OddEvenLinkedList().reorder(head);
        assertEquals(Arrays.asList(1, 3, 2), head.toJavaList());
    }

    @Test
    public void testReorder4() {
        final ListNode head = OddEvenLinkedList.createConsequent(4);
        new OddEvenLinkedList().reorder(head);
        assertEquals(Arrays.asList(1, 3, 2, 4), head.toJavaList());
    }

    @Test
    public void testReorder5() {
        final ListNode head = OddEvenLinkedList.createConsequent(5);
        new OddEvenLinkedList().reorder(head);
        assertEquals(Arrays.asList(1, 3, 5, 2, 4), head.toJavaList());
    }

    @Test
    public void testReorder6() {
        final ListNode head = OddEvenLinkedList.createConsequent(6);
        new OddEvenLinkedList().reorder(head);
        assertEquals(Arrays.asList(1, 3, 5, 2, 4, 6), head.toJavaList());
    }
}