package kuvaldis.algorithm.programcreek;

import java.util.ArrayList;
import java.util.List;

/*

Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

 */
public class OddEvenLinkedList {

    public static class ListNode {
        public final int value;
        public ListNode next;

        public ListNode(final int value) {
            this.value = value;
        }

        public List<Integer> toJavaList() {
            final List<Integer> list = new ArrayList<>();
            ListNode current = this;
            while (current != null) {
                list.add(current.value);
                current = current.next;
            }
            return list;
        }
    }

    public void reorder(final ListNode head) {
        if (head == null || head.next == null) {
            return;
        }    

        final ListNode[] pointers = new ListNode[2]; // 0 for odds, 1 for evens
        final ListNode evenHead = head.next;
        ListNode prevActive = head;
        pointers[0] = head;
        pointers[1] = head.next;
        int active = 0;
        while (pointers[active].next != null) {
            final int nextActive = (active + 1) % 2;
            pointers[active].next = pointers[nextActive].next;
            prevActive = pointers[active];
            pointers[active] = pointers[active].next;
            active = nextActive;
        }
        if (active == 1) { // previous active is odd, point to even head
            prevActive.next = evenHead;
        } else { // previous active is even, next active is the last odd
            pointers[active].next = evenHead;
        }
    }

    public static ListNode createConsequent(final int numberOfNodes) {
        final ListNode head = new ListNode(1);
        ListNode current = head;
        for (int i = 1; i < numberOfNodes; i++) {
            current.next = new ListNode(i + 1);
            current = current.next;
        }
        return head;
    }
}
