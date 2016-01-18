package kuvaldis.algorithm.cake;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TwoStackQueueTest {
    @Test
    public void testStackQueue() throws Exception {
        final TwoStackQueue queue = new TwoStackQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue());
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        queue.enqueue(6);
        queue.enqueue(7);
        assertEquals(4, queue.dequeue());
        assertEquals(5, queue.dequeue());
        assertEquals(6, queue.dequeue());
        assertEquals(7, queue.dequeue());
    }
}