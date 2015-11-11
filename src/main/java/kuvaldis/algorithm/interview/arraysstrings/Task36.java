package kuvaldis.algorithm.interview.arraysstrings;

import java.util.Deque;

public class Task36 {

    final void sort(final Deque<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int first = stack.pop();
        if (stack.isEmpty()) {
            stack.push(first);
            return;
        }
        int second = stack.pop();
        if (first > second) {
            stack.push(first);
            sort(stack);
            first = stack.pop();
            if (first < second) {
                stack.push(second);
                sort(stack);
                stack.push(first);
            } else {
                stack.push(first);
                stack.push(second);
            }
        } else {
            stack.push(second);
            sort(stack);
            second = stack.pop();
            if (second < first) {
                stack.push(first);
                sort(stack);
                stack.push(second);
            } else {
                stack.push(second);
                stack.push(first);
            }
        }
    }
}
