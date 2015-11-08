package kuvaldis.algorithm.interview.arraysstrings;

public class Task21 {

    public <T> Node<T> removeDuplicates(final Node<T> head) {
        if (head == null) {
            return null;
        }
        Node<T> current = head;
        while (current != null) {
            Node<T> runner = current.next;
            while (runner != null) {
                final Node<T> next = runner.next;
                if (current.val.equals(runner.val)) {
                    final Node<T> prev = runner.prev;
                    prev.next = next;
                    runner.next = null;
                    runner.prev = null;
                    if (next != null) {
                        next.prev = prev;
                    }
                }
                runner = next;
            }
            current = current.next;
        }
        return head;
    }
}
