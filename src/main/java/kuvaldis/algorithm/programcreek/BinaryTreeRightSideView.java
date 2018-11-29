package kuvaldis.algorithm.programcreek;

import java.util.*;

/*

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom. For example, given the following binary tree,

   1            <---
 /   \
2     3         <---
 \
  5             <---
You can see [1, 3, 5].

 */
public class BinaryTreeRightSideView {

    public static class Node {
        public final int value;
        public final Node left;
        public final Node right;

        public Node(final int value,
                    final Node left,
                    final Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> getRightSideView(final Node root) {
        Node current = root;
        int currentIndex = 0;
        final Stack<Node> stack = new Stack<>();
        final List<Integer> result = new ArrayList<>();

        while (current != null || !stack.isEmpty()) {
            if (current == null) { 
                final Node parent = stack.pop();
                if (parent != null && parent.left != null) {
                    current = parent.left;
                } else {
                    currentIndex--;
                    continue;
                }
            }
            if (result.size() == currentIndex) {
                result.add(current.value);
            }
            if (current.right != null) {
                stack.push(current);  
                current = current.right;
                currentIndex++;
            } else if (current.left != null) {
                stack.push(null);
                current = current.left;
                currentIndex++;
            } else {
                current = null;
            }
        }

        return result;
    }
}
