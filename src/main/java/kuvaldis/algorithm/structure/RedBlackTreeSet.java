package kuvaldis.algorithm.structure;

import lombok.Data;

import static java.util.Optional.ofNullable;

/**
 * Balanced tree based on red-black implementation set
 * <p>
 * 1. Each node is either red or black.
 * 2. Root is black
 * 3. All the leaves (e.g. null nodes in this implementation) are black
 * 4. Red node's children are both black.
 * 5. Every simple path from current node to a leaf consists of the same black nodes amount
 *
 * @param <T> node value param
 */
public class RedBlackTreeSet<T extends Comparable<T>> {

    private enum Color {
        RED, BLACK
    }

    @Data
    private static class Node<T> {
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;
        private Color color = Color.BLACK;
        T value;

        private Node(final T value,
                     final Node<T> parent) {
            this.value = value;
            this.parent = parent;
        }
    }

    private Node<T> root;

    public RedBlackTreeSet() {

    }

    private static <T> Node<T> parent(final Node<T> x) {
        return ofNullable(x).map(Node::getParent).orElse(null);
    }

    private static <T> Node<T> left(final Node<T> x) {
        return ofNullable(x).map(Node::getLeft).orElse(null);
    }

    private static <T> Node<T> right(final Node<T> x) {
        return ofNullable(x).map(Node::getRight).orElse(null);
    }

    private static <T> Color getColor(final Node<T> x) {
        return ofNullable(x).map(Node::getColor).orElse(Color.BLACK);
    }

    private static <T> void setColor(final Node<T> x, final Color color) {
        if (x != null) {
            x.color = color;
        }
    }

    public void add(final T value) {
        Node<T> node = root;
        if (node == null) {
            root = new Node<>(value, null);
            return;
        }
        Node<T> parent;
        int compareResult;
        do {
            parent = node;
            compareResult = value.compareTo(node.value);
            if (compareResult < 0) {
                node = node.left;
            } else if (compareResult > 0) {
                node = node.right;
            } else {
                return;
            }
        } while (node != null);
        Node<T> newNode = new Node<>(value, parent);
        if (compareResult < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        insertFix(newNode);
    }

    public void delete(final T value) {
        Node<T> node = getNode(value);
        if (node == null) {
            return;
        }
        // in case if we need to remove a node with both children better to get successor which will not have left child
        // put it's value to removing node's value and remove the successor.
        if (node.left != null && node.right != null) {
            final Node<T> s = successor(node);
            node.value = s.value;
            node = s;
        }
        // replace by it's child and balance if the node is black
        Node<T> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            node.parent = node.left = node.right = null;
            if (node.color == Color.BLACK) {
                deleteFix(replacement);
            }
        } else if (node.parent == null) { // the node is the last one. this is remove case number 1.
            root = null;
        } else { // here we are going to act with the node as a phantom and unlink it
            if (node.color == Color.BLACK) {
                deleteFix(node);
            }
            if (node.parent != null) {
                if (node == node.parent.left) {
                    node.parent.left = null;
                } else if (node == node.parent.right) {
                    node.parent.right = null;
                }
                node.parent = null;
            }
        }
    }

    private static <T> Node<T> successor(final Node<T> node) {
        if (node == null) {
            return null;
            // node to the right is greater than current. the last left (distant) in right subtree is greatest of them
        } else if (node.right != null) {
            Node<T> p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else {
            // there is no right subtree for current node. go up till current node is left child of parent.
            // this parent is the next greatest node after given.
            Node<T> p = node.parent;
            Node<T> ch = node;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    private static <T> Node<T> predecessor(final Node<T> node) {
        if (node == null) {
            return null;
        } else if (node.left != null) {
            Node<T> p = node.left;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        } else {
            Node<T> p = node.parent;
            Node<T> ch = node;
            while (p != null && ch == p.left) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    private Node<T> getNode(final T value) {
        Node<T> node = root;
        while (node != null) {
            final int compareResult = value.compareTo(node.value);
            if (compareResult < 0) {
                node = node.left;
            } else if (compareResult > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * Three cases here:
     * 1. node == root. Node is root. Nothing to do here. Just make it black (2 property).
     * 2. node.parent.color == Color.BLACK. Parent's color is black. Since current node is red it doesn't violate any property.
     * 3. node.parent.color == Color.RED. There can be two cases: uncle is either red or black.
     * @param x - current inserted node
     */
    private void insertFix(final Node<T> x) {
        Node<T> node = x;
        node.color = Color.RED;

        // while check covers 1 and 2 case
        while (node != null && node != root && node.parent.color == Color.RED) {
            // in fact it's for determining uncle node. the logic is symmetric
            if (parent(node) == left(parent(parent(node)))) {
                final Node<T> uncle = right(parent(parent(node)));
                // parent is red as well as uncle
                if (getColor(uncle) == Color.RED) {
                    setColor(parent(node), Color.BLACK);
                    setColor(uncle, Color.BLACK);
                    // because (5) may be violated, so it balances black nodes for current subtree
                    setColor(parent(parent(node)), Color.RED);
                    // grandparent's parent might be red, so grandparent might violate (4)
                    node = parent(parent(node));
                } else { // parent is red, whereas uncle is black
                    // node is right child, we need to change it to the next case,
                    // so current node becomes parent and parent becomes left child.
                    // (5) is not violated, only (4).
                    if (node == right(parent(node))) {
                        node = parent(node);
                        rotateLeft(node);
                    }
                    // grandparent has to be black because current node (former parent) is red.
                    // so change their colors and make right rotation for grandparent.
                    // after that grandparent is right child for parent and it's red, while parent is black
                    // so all the paths go through parent instead of grandparent now, so (5) is not violated
                    // that's final step for balancing since parent is black for current tree and this is the second major case
                    setColor(parent(node), Color.BLACK);
                    setColor(parent(parent(node)), Color.RED);
                    rotateRight(parent(parent(node)));
                }
            } else {
                // just symmetrical
                final Node<T> uncle = left(parent(parent(node)));
                if (getColor(uncle) == Color.RED) {
                    setColor(parent(node), Color.BLACK);
                    setColor(uncle, Color.BLACK);
                    setColor(parent(parent(node)), Color.RED);
                    node = parent(parent(node));
                } else {
                    if (node == left(parent(node))) {
                        node = parent(node);
                        rotateRight(node);
                    }
                    setColor(parent(node), Color.BLACK);
                    setColor(parent(parent(node)), Color.RED);
                    rotateLeft(parent(parent(node)));
                }
            }
        }

        // for the purpose of (2)
        root.color = Color.BLACK;
    }

    private void deleteFix(final Node<T> x) {
        Node<T> node = x;
        // checks 1, 2, 3 cases
        while (node != root && getColor(node) == Color.BLACK) {
            if (node == left(parent(node))) {
                Node<T> sibling = right(parent(node));
                // case number 2. sibling is red. change parent and sibling colors and rotate left. parent now red
                // after that new sibling is black because it was a child of previous sibling which was red
                if (getColor(sibling) == Color.RED) {
                    setColor(sibling, Color.BLACK);
                    setColor(parent(sibling), Color.RED);
                    rotateLeft(parent(node));
                    sibling = right(parent(node));
                }
                // for the second case this is the last step in the loop. so except of excluding node x the last things
                // we have to do is change parent's color which will be done in the end of the method and also new sibling
                // have to be red
                // this is also for the 3 case. when the sibling is black we just need to color it with red and go to 1 case
                // also it covers the 4 case when parent is red and sibling is black. we change sibling to red here
                // and parent to black in the end of the method
                if (getColor(left(sibling)) == Color.BLACK &&
                        getColor(right(sibling)) == Color.BLACK) {
                    setColor(sibling, Color.RED);
                    node = parent(node);
                } else { // 5 case. sibling is black, right sibling's child is black, so left should be red here.
                    // so change colors of sibling and it's left child and rotate it
                    // after that the node's new sibling is former left child of previous sibling which is also parent of former sibling
                    // go to case 6
                    if (getColor(right(sibling)) == Color.BLACK) {
                        setColor(left(sibling), Color.BLACK);
                        setColor(sibling, Color.RED);
                        rotateRight(sibling);
                        // new sibling's is red as it's former sibling
                        sibling = right(parent(node));
                    }
                    // case 6. parent's color doesn't matter. sibling's right child is red. node and sibling are black
                    // save color for parent's place as sibling becomes new parent
                    setColor(sibling, getColor(parent(node)));
                    // others are black
                    setColor(parent(node), Color.BLACK);
                    setColor(right(sibling), Color.BLACK);
                    rotateLeft(parent(node));
                    // final step
                    node = root;
                }
            } else { // symmetric
                Node<T> sibling = left(parent(node));
                if (getColor(sibling) == Color.RED) {
                    setColor(sibling, Color.BLACK);
                    setColor(parent(sibling), Color.RED);
                    rotateRight(parent(node));
                    sibling = left(parent(node));
                }
                if (getColor(right(sibling)) == Color.BLACK &&
                        getColor(left(sibling)) == Color.BLACK) {
                    setColor(sibling, Color.RED);
                    node = parent(node);
                } else {
                    if (getColor(left(sibling)) == Color.BLACK) {
                        setColor(right(sibling), Color.BLACK);
                        setColor(sibling, Color.RED);
                        rotateLeft(sibling);
                        sibling = left(parent(node));
                    }
                    setColor(sibling, getColor(parent(node)));
                    setColor(parent(node), Color.BLACK);
                    setColor(left(node), Color.BLACK);
                    rotateRight(parent(node));
                    node = root;
                }
            }
        }

        // for the 2 case
        setColor(node, Color.BLACK);
    }

    // right node goes to node, node becomes its left node, right's left node becomes node's right node
    private void rotateLeft(final Node<T> node) {
        final Node<T> right = node.right;

        // establish node.right link
        node.right = right.left;
        if (right.left != null) {
            right.left.parent = node;
        }

        // establish right.parent link
        right.parent = node.parent;
        if (node.parent == null) {
            root = right;
        } else if (node == node.parent.left) {
            node.parent.left = right;
        } else {
            node.parent.right = right;
        }

        // link node and right
        right.left = node;
        node.parent = right;
    }

    // left node goes to node, node becomes its right node, left's right node becomes node's left node
    private void rotateRight(final Node<T> node) {
        final Node<T> left = node.left;

        // establish node.left link
        node.left = left.right;
        if (left.right != null) {
            left.right.parent = node;
        }

        // establish left.parent link
        left.parent = node.parent;
        if (node.parent == null) {
            root = left;
        } else if (node == node.parent.left) {
            node.parent.left = left;
        } else {
            node.parent.right = left;
        }

        // link node and left
        left.right = node;
        node.parent = left;
    }
}
