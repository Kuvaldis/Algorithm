package kuvaldis.algorithm.cake;

/*

Hooray! It's opposite day. Linked lists go the opposite way today.
Write a function for reversing a linked list ? . Do it in-place ? .

Your function will have one input: the head of the list.

Your function should return the new head of the list.

Here's a sample linked list node class:

  public static class LinkedListNode {

    public int value;
    public LinkedListNode next;

    public LinkedListNode(int value) {
        this.value = value;
    }
}

 */
public class ReverseLinkedList {
    public static class LinkedListNode {

        public int value;
        public LinkedListNode next;

        public LinkedListNode(int value) {
            this.value = value;
        }
    }

    public LinkedListNode reverse(final LinkedListNode list) {
        if (list == null) {
            return null;
        }
        LinkedListNode prev = list;
        LinkedListNode node = list.next;
        if (node == null) {
            return list;
        }
        LinkedListNode next = node.next;
        prev.next = null;
        while (node != null) {
            node.next = prev;
            prev = node;
            node = next;
            if (next != null) {
                next = next.next;
            }
        }
        return prev;
    }
}
