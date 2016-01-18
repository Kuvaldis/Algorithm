package kuvaldis.algorithm.cake;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

/*

Implement a queue with 2 stacks. Your queue should have an enqueue and a dequeue function and it should be
"first in first out" (FIFO).
Optimize for the time cost of mm function calls on your queue. These can be any mix of enqueue and dequeue calls.

Assume you already have a stack implementation and it gives O(1) time push and pop.

 */
public class TwoStackQueue {

    private final Deque<Integer> enqueueStack = new ArrayDeque<>();
    private final Deque<Integer> dequeStack = new ArrayDeque<>();

    public void enqueue(final int i) {
        enqueueStack.push(i);
    }

    public int dequeue() {
        // for m calls will be O(m) overall. Each element is transferred to dequeueStack only once
        if (dequeStack.isEmpty()) {
            while (!enqueueStack.isEmpty()) {
                dequeStack.push(enqueueStack.pop());
            }
        }
        return dequeStack.pop();
    }

}
