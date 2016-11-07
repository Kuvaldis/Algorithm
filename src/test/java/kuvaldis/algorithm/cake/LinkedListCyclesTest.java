package kuvaldis.algorithm.cake;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListCyclesTest {

    @Test
    public void testHasCycle() throws Exception {
        LinkedListCycles.LinkedListNode node0 = new LinkedListCycles.LinkedListNode(0);
        LinkedListCycles.LinkedListNode node1 = new LinkedListCycles.LinkedListNode(1);
        LinkedListCycles.LinkedListNode node2 = new LinkedListCycles.LinkedListNode(2);
        LinkedListCycles.LinkedListNode node3 = new LinkedListCycles.LinkedListNode(3);
        LinkedListCycles.LinkedListNode node4 = new LinkedListCycles.LinkedListNode(4);
        LinkedListCycles.LinkedListNode node5 = new LinkedListCycles.LinkedListNode(5);
        LinkedListCycles.LinkedListNode node6 = new LinkedListCycles.LinkedListNode(6);

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        assertFalse(new LinkedListCycles().hasCycle(node0));

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node0;
        assertTrue(new LinkedListCycles().hasCycle(node0));

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node3;
        assertTrue(new LinkedListCycles().hasCycle(node0));
    }
}