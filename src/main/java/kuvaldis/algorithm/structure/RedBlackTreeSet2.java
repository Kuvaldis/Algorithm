package kuvaldis.algorithm.structure;

/**
 * Only integer implementation, just to illustrate algorithm and exclude redundant information
 * <p>
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
    int size;

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
        size++;
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
        } else if (node.parent.left == node && grandparent.right == node.parent) {
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

    public void delete(final int value) {
        Node node = findNode(value);
        if (node == null) {
            return;
        }

        // for the case when the node to delete has two non NIL children we have to transform it
        // to the node with one or zero children
        // it may be done either finding successor or predecessor of the node.
        // 1. successor - the lowest node in the right subtree (last left node without left node)
        // 2. predecessor - the highest node in the left subtree (last right node without right node)
        // we choose successor and change it's value to the value of the node to delete.
        // the tree is still a search tree.
        if (node.left != null && node.right != null) {
            final Node successor = successor(node);
            node.value = successor.value;
            node = successor;
        }
        size--;
        deleteNodeWithOneOrZeroChildren(node);
    }

    private Node findNode(final int value) {
        if (root == null) {
            return null;
        }
        Node node = root;
        while (node != null) {
            if (node.value == value) {
                break;
            }
            if (value < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    // Here we remove the node with either non NIL left or right or both NIL children and make it red-black if there are rule violations
    // After the replacement check node's color. There are two cases here:
    // 1. The replacement is not null. Replace first. Then there are 2 subcases:
    // 1.1. The replacement is red. Make it black, nothing is violated.
    // 1.2. The replacement is black. Should be fixed as property number 5 is violated.
    // 2. The replacement is null. Act with the node as 'phantom'. First rebalance the tree if it's black,
    //    second replace it (with null actually).
    private void deleteNodeWithOneOrZeroChildren(final Node node) {
        final Node replacement = node.left != null ? node.left : node.right;

        if (replacement != null) {
            replaceNode(node, replacement);
            if (node.color == Color.Black) {
                if (replacement.color == Color.Red) {
                    replacement.color = Color.Black;
                } else {
                    deleteCase1(replacement);
                }
            }
        } else {
            if (node.color == Color.Black) {
                deleteCase1(node);
            }
            replaceNode(node, null);
        }
    }

    // here we suppose the node has both left and right children
    private Node successor(final Node node) {
        Node result = node.right;
        while (result.left != null) {
            result = result.left;
        }
        return result;
    }

    // There are three possible cases here:
    // 1. The node is root
    // 2. The node is left child
    // 3. The node is right child
    private void replaceNode(final Node node, final Node replacement) {
        // in any case replacement's parent should be node's parent
        if (replacement != null) {
            replacement.parent = node.parent;
        }
        if (node.parent == null) {
            root = replacement;
        } else if (node.parent.left == node) {
            node.parent.left = replacement;
        } else {
            node.parent.right = replacement;
        }
        // in any case there shouldn't be any links from the node
        node.parent = node.left = node.right = null;
    }

    // from here the node to work on is always black
    // delete cases are needed to add one more black node to deleted node subtree
    // or making sibling subtree have one less black node with sibling subtree rebalancing.

    // Here the node doesn't have any non-NIL child already
    // Check if it's the root
    private void deleteCase1(final Node node) {
        // no parent, means it's the root
        if (node.parent == null) {
            return;
        }
        deleteCase2(node);
    }


    // The node is not the root, has parent. Still doesn't have any non-NIL child
    // Check if sibling is red. If it is, make parent red, and sibling black. Then there are two cases:
    // 1. The node is left child. Rotate parent to the left.
    // 2. The node is right child. Rotate parent to the right.
    // In any way go to delete case 3.
    /*
              |                                  |
              P(b)                               S(b)
             /  \                               /   \
          N(b)   S(r)        ---------->      P(r)   Sr(b)
         /  \    /   \                       /  \     /  \
        1    2 Sl(b)  Sr(b)               N(b) Sl(b) 5    6
              /  \   /  \                /  \   / \
             3    4 5    6              1   2  3   4
    */
    private void deleteCase2(final Node node) {
        final Node sibling = sibling(node);
        if (sibling != null && sibling.color == Color.Red) {
            sibling.color = Color.Black;
            node.parent.color = Color.Red;
            if (node.parent.left == node) {
                rotateLeft(node.parent);
            } else {
                rotateRight(node.parent);
            }
        }
        deleteCase3(node);
    }

    // The node is not the root, has parent
    // Check if parent, sibling and sibling's children are black. If so, set sibling to red
    // and rebalance tree for parent, because even though node's parent's subtree is balanced,
    // after node's removing and the subtree has less black numbers, then parent's sibling's subtree,
    // so property 5 is violated.
    /*
              |                                  |
              P(b)                               P(b)
             /  \                               /  \
          N(b)   S(b)        ---------->     N(b)   S(r)
         /  \    /   \                      /  \    /   \
        1    2 Sl(b)  Sr(b)                1    2 Sl(b)  Sr(b)
              /  \   /  \                        /  \   /  \
             3    4 5    6                      3    4 5    6
    */
    private void deleteCase3(final Node node) {
        final Node sibling = sibling(node);
        if (node.parent.color == Color.Black &&
                sibling.color == Color.Black &&
                (sibling.left == null || sibling.left.color == Color.Black) &&
                (sibling.right == null || sibling.right.color == Color.Black)) {
            sibling.color = Color.Red;
            deleteCase1(node.parent);
        } else {
            deleteCase4(node);
        }
    }

    // the node is not the root and has parent
    // The same case as delete case 3 but parent should be red
    /*
              |                                  |
              P(r)                               P(b)
             /  \                               /  \
          N(b)   S(b)        ---------->     N(b)   S(r)
         /  \    /   \                      /  \    /   \
        1    2 Sl(b)  Sr(b)                1    2 Sl(b)  Sr(b)
              /  \   /  \                        /  \   /  \
             3    4 5    6                      3    4 5    6
    */
    private void deleteCase4(final Node node) {
        final Node sibling = sibling(node);
        if (node.parent.color == Color.Red &&
                sibling.color == Color.Black &&
                (sibling.left == null || sibling.left.color == Color.Black) &&
                (sibling.right == null || sibling.right.color == Color.Black)) {
            sibling.color = Color.Red;
            node.parent.color = Color.Black;
        } else {
            deleteCase5(node);
        }
    }

    // when we come here the node is still black with parent (doesn't matter what color it has).
    // case 2 makes the node's sibling black and case 3 and 4 end up with sibling red when they don't go to case 5,
    // always having black sibling when go to the next case.
    // Two cases:
    // 1. If the node is left, check if it's sibling's left child is red and right is black.
    //    Make sibling red, it's left node black, rotate it to the right
    // 2. If the node is right, check if it's sibling's right child is red and left is black.
    //    Make sibling red, it's right node black, rotate it to the left
    /*
                |                                      |
                P                                      P
               /  \                                   /  \
            N(b)   S(b)       --------->           N(b)   Sl(b)
           /  \    /   \                          /  \    /   \
          1    2 Sl(r)  Sr(b)                    1    2  3    S(r)
                /  \   /  \                                   /  \
               3    4 5    6                                 4   Sr(b)
                                                                 /  \
                                                                5    6
     */
    private void deleteCase5(final Node node) {
        final Node sibling = sibling(node);
        if (node.parent.left == node &&
                sibling.left != null && sibling.left.color == Color.Red &&
                (sibling.right == null || sibling.right.color == Color.Black)) {
            sibling.color = Color.Red;
            sibling.left.color = Color.Red;
            rotateRight(sibling);
        } else if (node.parent.right == node &&
                sibling.right != null && sibling.right.color == Color.Red &&
                (sibling.left == null || sibling.left.color == Color.Black)) {
            sibling.color = Color.Red;
            sibling.right.color = Color.Black;
            rotateLeft(sibling);
        }
        deleteCase6(node);
    }

    // after case 5 the node is still black, has parent and black sibling.
    // If the node is left child, then the sibling has right red child.
    // If the node is right child, then the sibling has left red child.
    // Make sibling's red child black and rotate parent. Make sibling's color the same as parent and parent black.
    // It doesn't change black number in paths not going through the node. The node's paths still have one more black node.
    /*
            |                                 |
            P                                 S
           /  \                             /   \
        N(b)   S(b)       --------->       P(b)  Sr(b)
       /  \    /   \                      /  \   /   \
      1    2  3    Sr(r)               N(b)   3 4     5
                  /  \                 /  \
                 4    5               1    2
    */
    private void deleteCase6(final Node node) {
        final Node sibling = sibling(node);
        sibling.color = node.parent.color;
        node.parent.color = Color.Black;

        if (node.parent.left == node) {
            sibling.right.color = Color.Black;
            rotateLeft(node.parent);
        } else {
            sibling.left.color = Color.Black;
            rotateRight(node.parent);
        }
    }

    private Node sibling(final Node node) {
        if (node.parent.left == node) {
            return node.parent.right;
        } else {
            return node.parent.left;
        }
    }

    public int size() {
        return size;
    }
}
