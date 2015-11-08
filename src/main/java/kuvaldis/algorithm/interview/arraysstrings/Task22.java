package kuvaldis.algorithm.interview.arraysstrings;

public class Task22 {

    public <T> Node<T> findFromLast(final Node<T> head, final int n) {
        if (head == null) {
            return null;
        }
        Node<T> runner = head;
        int i = n;
        while (i > 0 && runner.next != null) {
            i--;
            runner = runner.next;
        }
        if (i != 0) {
            return null;
        }
        Node<T> result = head;
        while (runner.next != null) {
            runner = runner.next;
            result = result.next;
        }
        return result;
    }
}
