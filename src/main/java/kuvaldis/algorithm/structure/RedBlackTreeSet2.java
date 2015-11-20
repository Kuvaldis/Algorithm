package kuvaldis.algorithm.structure;

/**
 * Only integer implementation, just to illustrate algorithm and exclude redundant information
 *
 * Red-black tree is a binary search tree with the following properties:
 * 1. Each node is either black or red
 * 2. The root is black
 * 3. All the NIL nodes are black
 * 4. Both children of a red subtree are black
 * 5. Any simple path from any node to any NIL node has the same black nodes number in between
 */
public class RedBlackTreeSet2 {

    enum Color {
        Black, Red
    }

    class Node {
        Node left;
        Node right;
        private Node parent;
        Color color;
        private int value;
    }

    Node root;

    /*
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

    /*
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
    public void insert(final int value) {
        final Node node = new Node();
        node.value = value;
        node.color = Color.Red;
        if (root == null) {
            root = node;
        } else {
            Node parent = root;
            while (true) {
                if (node.value < parent.value) {
                    if (parent.left == null) {
                        parent.left = node;
                        node.parent = parent;
                        break;
                    } else {
                        parent = parent.left;
                    }
                } else if (parent.value < node.value) {
                    if (parent.right == null) {
                        parent.right = node;
                        node.parent = parent;
                        break;
                    } else {
                        parent = parent.right;
                    }
                } else {
                    // this is a set, no duplicates allowed
                    return;
                }
            }
        }
        insertCase1(node);
    }

    // if the node is root. paint it black, so property 5 will not be violated
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

    // the node is not root and it's parent is red.
    // Hence it's parent is not root either since by property 2 the root is always black
    /*
                    |                           |
                    G(b)                        G(r)
                   / \                         / \
                P(r)  U(r)  ---------->     P(b)  U(b)
               / \   / \                   / \   / \
             N(r) 3 4   5                N(r) 3 4   5
             / \                         / \
            1   2                       1   2

        If parent and uncle are red
     */
    private void insertCase3(final Node node) {
        final Node uncle = uncle(node);
        // parent and uncle are red, just change it's color to black, and make grandparent red
        // so, property 5 is not violated, since all the paths from grandparent to leaves are left with the same black
        // nodes number
        if (uncle != null && uncle.color == Color.Red && node.parent.color == Color.Red) {
            node.parent.color = Color.Black;
            uncle.color = Color.Black;
            // if has an uncle, then grandparent exists anyway
            node.parent.parent.color = Color.Red;
            // now, properties might be violated for grandparent
            insertCase1(node.parent.parent);
        } else {
            insertCase4(node);
        }
    }

    // the node is not a root, there is a parent and it is red, but the node doesn't have red uncle.
    /*
             |                          |                           |                          |
             G(b)                       G(b)                        G(b)                       G(b)
            /  \                       /  \                        /  \                       /  \
         P(r)   U(b)  ---------->    N(r)  U(b)      OR         U(b) P(r)  ---------->      U(b)  N(r)
        / \     / \                  / \   / \                 / \   /   \                 / \    /  \
       1  N(r) 4   5               P(r) 3 4   5               1   2 N(r)  5               1   2  3   P(r)
          / \                      / \                             /  \                             / \
         2   3                    1   2                           3    4                           4   5
     */
    private void insertCase4(final Node node) {
        final Node grandparent = node.parent.parent;
        // if node is right child and it's parent is left grandparent's child, then rotate left
        if (node.parent.right == node && grandparent.left == node.parent) {
            rotateLeft(node.parent);
            insertCase5(node.left);
        } else if(node.parent.left == node && grandparent.right == node.parent) {
            rotateRight(node.parent);
            insertCase5(node.right);
        } else {
            insertCase5(node);
        }
    }

    // the node is not a root, there is a parent and it is red, but the node doesn't have red uncle.
    // (node is a left child and it's parent is a left child) or (node is a right child and it's parent is a left child)
    /*
               |                               |                          |                               |
               G(b)                            P(b)                       G(b)                            P(b)
              /  \                            /  \                       /  \                            /  \
            P(r)  U(b)   ------------->     N(r)  G(r)       OR        U(b)  P(r)    ------------>     G(r)  N(r)
            / \   / \                      / \    /  \                / \    /  \                      / \   / \
          N(r) 3 4   5                    1   2  3   U(b)            1   2  3   N(r)                 U(b) 3 4   5
          / \                                       / \                        / \                   / \
         1   2                                     4   5                      4   5                 1   2
     */
    private void insertCase5(final Node node) {
        final Node grandparent = node.parent.parent;
        // grandparent is black, because parent is red. change colors and rotate grandparent.
        node.parent.color = Color.Black;
        grandparent.color = Color.Red;
        if (grandparent.left == node.parent && node.parent.left == node) {
            rotateRight(grandparent);
        } else {
            rotateLeft(grandparent);
        }
    }

    private Node uncle(final Node node) {
        final Node parent = node.parent;
        if (parent != null && parent.parent != null) {
            if (parent.parent.left == parent) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }
        return null;
    }
}
