package kuvaldis.algorithm.cake;

/*

Delete a node from a singly-linked list ? , given only a variable pointing to that node.
The input could, for example, be the variable b below:

  public static class LinkedListNode {

    public String value;
    public LinkedListNode next;

    public LinkedListNode(String value) {
        this.value = value;
    }
}

LinkedListNode a = new LinkedListNode("A");
LinkedListNode b = new LinkedListNode("B");
LinkedListNode c = new LinkedListNode("C");

a.next = b;
b.next = c;

deleteNode(b);

 */
public class DeleteNode {

    public static class LinkedListNode {

        public String value;
        public LinkedListNode next;

        public LinkedListNode(String value) {
            this.value = value;
        }
    }

    public void delete(final LinkedListNode node) {
        if (node.next == null) { // last node to delete
            // imposibruu!
        } else {
            final LinkedListNode next = node.next;
            node.value = next.value;
            node.next = next.next;
            next.next = null;
        }
    }
}
