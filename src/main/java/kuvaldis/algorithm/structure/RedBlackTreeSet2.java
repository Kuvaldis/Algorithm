package kuvaldis.algorithm.structure;

/**
 * Only integer implementation, just to illustrate algorithm and exclude redundant information
 *
 * Red-black tree is a binary search tree with the following properties:
 * 1. Each node is either black or red
 * 2. The root is black
 * 3. All the NIL nodes are black
 * 4. Both children of a red tree are black
 * 5. Any simple path from any node to any NIL node has the same black nodes number in between
 */
public class RedBlackTreeSet2 {

    private enum Color {
        Black, Red
    }

    private class Node {
        private Node left;
        private Node right;
        private Node parent;
        private Color color;
        private int value;
    }

    private Node parent(final Node node) {
        if (node != null) {
            return node.parent;
        }
        return null;
    }

    private Node left(final Node node) {
        if (node != null) {
            return node.left;
        }
        return null;
    }

    private Node right(final Node node) {
        if (node != null) {
            return node.right;
        }
        return null;
    }

    private Color color(final Node node) {
        if (node != null) {
            return node.color;
        }
        return Color.Black;
    }

    private void setColor(final Node node, final Color color) {
        if (node != null) {
            node.color = color;
        }
    }

    private Node root;

    /**
     * It's supposed that x has right child
     *
     *       |                           |
     *       y                           x
     *     /  \                        /  \
     *    x    C    <--------------   A    y
     *  /  \        left-rotate(x)       /  \
     * A   B                            B   C
     *
     */
    private void rotateLeft(final Node x) {
        //noinspection SuspiciousNameCombination
        final Node y = x.right;

        // make B x's right child
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        // make x's parent y's parent
        y.parent = x.parent;
        if (x.parent == null) { // no parent? x was the root, y is the root now
            root = y;
        } else {
            if (x.parent.left == x) { // if x was left child, then make y x's parent's left child
                //noinspection SuspiciousNameCombination
                x.parent.left = y;
            } else { // otherwise right child
                //noinspection SuspiciousNameCombination
                x.parent.right = y;
            }
        }

        // make y x's parent
        y.left = x;
        x.parent = y;
    }

    /**
     * It's supposed that x has left child
     *
     *       |      right-rotate(y)      |
     *       x      -------------->      y
     *     /  \                        /  \
     *    y    C                      A    x
     *  /  \                             /  \
     * A   B                            B   C
     *
     */
    private void rotateRight(final Node x) {
        //noinspection SuspiciousNameCombination
        final Node y = x.left;

        // make B x's left child
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }

        // make x's parent y's parent
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else {
            if (x.parent.left == x) {
                //noinspection SuspiciousNameCombination
                x.parent.left = y;
            } else {
                //noinspection SuspiciousNameCombination
                x.parent.right = y;
            }
        }

        // make y x's parent
        x.parent = y;
        y.right = x;
    }

    // insert red node always

    // the node is root. paint it black, so property 5 will not be violated
    private void insertCase1(final Node node) {
        if (node.parent == null) {
            node.color = Color.Black;
        } else {
            insertCase2(node);
        }

    }

    // the node is not root.
    // if the parent is black, then property 5 is not violated, since number of blacks hasn't changed
    private void insertCase2(final Node node) {
        if (node.parent.color == Color.Black) {
            return;
        }
        insertCase3(node);
    }

    // the node is not root and it's parent is red. Hence it's parent is not root either since by property 2 the root is always black
    private void insertCase3(final Node node) {

    }
}
