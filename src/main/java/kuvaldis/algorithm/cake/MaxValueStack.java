package kuvaldis.algorithm.cake;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxValueStack {
    private final Deque<Integer> stack = new ArrayDeque<>();
    private final Deque<Integer> maximums = new ArrayDeque<>();

    public void push(final int value) {
        stack.push(value);
        if (maximums.isEmpty() || maximums.peek() <= value) {
            maximums.push(value);
        }
    }

    public int pop() {
        final int value = stack.pop();
        if (value == maximums.peek()) {
            maximums.pop();
        }
        return value;
    }

    public int getMax() {
        return maximums.peek();
    }
}
