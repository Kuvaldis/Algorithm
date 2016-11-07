package kuvaldis.algorithm.cake;

/*

You have a singly-linked list ? and want to check if it contains a cycle.
A singly-linked list is built with nodes, where each node has:

node.next—the next node in the list.
node.data—the data held in the node. For example, if our linked list stores people in line at the movies, node.data might be the person's name.
For example:

  public static class LinkedListNode {

    public int value;
    public LinkedListNode next;

    public LinkedListNode(int value) {
        this.value = value;
    }
}

A cycle occurs when a node’s next points back to a previous node in the list. The linked list is no longer linear with a beginning and end—instead, it cycles through a loop of nodes.

Write a function containsCycle() that takes the first node in a singly-linked list and returns a boolean indicating whether the list contains a cycle.

 */
public class LinkedListCycles {
    public static class LinkedListNode {

        public int value;
        public LinkedListNode next;

        public LinkedListNode(int value) {
            this.value = value;
        }
    }

    // We are using two pointers. One moves with a speed of 1 and the second with 2.
    // Then when the first achieves cycle point, the second has passed twice the first's distance.
    // The second has handicap equals to the length of non cycled part length mod cycle length.
    // Move them until they meet. The length of distance to the cycle point from the meeting point
    // will be again the same as the non cycled part length mod cycle length.
    // To find the exact point do the following:
    // Move the third pointer from the head and the first with the same speed of 1.
    // The meeting point will be the cycle point
    public boolean hasCycle(final LinkedListNode head) {
        LinkedListNode first = head;
        LinkedListNode second = head;

        while (second != null) {
            first = first.next;
            second = second.next;
            if (second != null) {
                second = second.next;
            }
            if (second != null && first == second) {
                // second and first met. There is a cycle
                return true;
            }
        }
        return false;
    }
}
