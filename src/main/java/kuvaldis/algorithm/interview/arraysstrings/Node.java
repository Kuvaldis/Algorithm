package kuvaldis.algorithm.interview.arraysstrings;

import java.util.LinkedList;
import java.util.List;

public class Node<T> {
    T val;
    Node<T> next;
    Node<T> prev;

    public static <T> List<T> toList(final Node<T> head) {
        Node<T> runner = head;
        final List<T> result = new LinkedList<>();
        while (runner != null) {
            result.add(runner.val);
            runner = runner.next;
        }
        return result;
    }

    public static <T> Node<T> fromList(final List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        Node<T> prev = null;
        for (T val : list) {
            final Node<T> newNode = new Node<>();
            if (prev != null) {
                prev.next = newNode;
            }
            newNode.prev = prev;
            newNode.val = val;
            prev = newNode;
        }
        if (prev == null) {
            return null;
        }
        while (prev.prev != null) {
            prev = prev.prev;
        }
        return prev;
    }
}
