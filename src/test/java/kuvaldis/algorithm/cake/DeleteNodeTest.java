package kuvaldis.algorithm.cake;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteNodeTest {

    @Test
    public void testDelete() throws Exception {
        final DeleteNode.LinkedListNode a = new DeleteNode.LinkedListNode("A");
        final DeleteNode.LinkedListNode b = new DeleteNode.LinkedListNode("B");
        final DeleteNode.LinkedListNode c = new DeleteNode.LinkedListNode("C");
        a.next = b;
        b.next = c;
        new DeleteNode().delete(b);
        assertEquals("A", a.value);
        assertEquals("C", a.next.value);
        assertNull(a.next.next);
    }
}