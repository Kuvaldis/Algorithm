package kuvaldis.algorithm.programcreek;

/*

Given perfect binary tree

         1
       /  \
      2    3
     / \  / \
    4  5  6  7

    should become

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL

 */
public class TreeNextRightPointers {

    public static class TreeLinkNode {
        public final int value;
        public final TreeLinkNode left;
        public final TreeLinkNode right;
        public TreeLinkNode next;

        public TreeLinkNode(final int value,
                            final TreeLinkNode left,
                            final TreeLinkNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public void populate(final TreeLinkNode root) {
        if (root == null) {
            return;
        }

        TreeLinkNode head = root;
        TreeLinkNode levelStart = root.left;
        while (head != null && head.left != null) {
            TreeLinkNode left = head.left;
            TreeLinkNode right = head.right;
            left.next = right;
            if (head.next == null) {
                head = levelStart;
                if (levelStart != null) {
                    levelStart = levelStart.left;
                }
            } else {
                head = head.next;
                right.next = head.left;                
            }
        }
    }
}
