package kuvaldis.algorithm.structure;

/**
 * Sorted set based on B-tree implementation
 */
public class BTreeSet {

    // even and greater than 2
    private static final int M = 4;

    private Node root;
    private int height;
    private int size;

    // for the purpose of simplicity I use one additional cell for children and one for values
    private static final class Node {
        private final int[] values = new int[M + 1];
        private int length;
        private Node[] children = new Node[M + 2];
    }

    public BTreeSet() {
        root = new Node();
    }

    public void add(final int value) {
        final Node newRoot = insert(root, value);
        size++;
        if (newRoot == null) return;
        root = newRoot;
        height++;
    }


    // attempt to insert value into the node.
    // if level is equal to tree height, then we couldn't manage to insert it to existing nodes
    // returns non null value as an additional child node
    private Node insert(final Node insertIntoNode, final int value) {
        Node childNewRoot = null;
        // assume M = 4 and the node is like []1[]8[]16[]18[]
        // there are 4 compares (with 1, 8, 16, 18) and the last case when it's higher than 18,
        // means we should go to the last child
        int insertPosition;
        for (insertPosition = 0; insertPosition <= insertIntoNode.length; insertPosition++) {
            if (insertPosition == insertIntoNode.length || value < insertIntoNode.values[insertPosition]) {
                final Node child = insertIntoNode.children[insertPosition];
                if (child != null) {
                    childNewRoot = insert(child, value);
                    if (childNewRoot == null) {
                        return null;
                    }
                }
                break;
            }
        }

        // if we are here, then either there is childNewRoot to merge into current or the value should be added into current
        if (childNewRoot != null) {
            insertChildrenRoot(insertIntoNode, childNewRoot, insertPosition);
            if (insertIntoNode.length > M) {
                return split(insertIntoNode);
            }
        } else {
            insertValue(insertIntoNode, value, insertPosition);
            if (insertIntoNode.length > M) {
                return split(insertIntoNode);
            }
        }
        return null;
    }

    // runs when children node has been split on two and new root should be inserted to the current node
    private void insertChildrenRoot(final Node insertIntoNode, final Node childrenRoot, final int position) {
        for (int i = childrenRoot.length; i > position; i--) {
            childrenRoot.values[i] = childrenRoot.values[i - 1];
            childrenRoot.children[i + 1] = childrenRoot.children[i];
        }
        insertIntoNode.values[position] = childrenRoot.values[0];
        insertIntoNode.children[position] = childrenRoot.children[0];
        insertIntoNode.children[position + 1] = childrenRoot.children[1];
        insertIntoNode.length++;
    }

    // if we insert value, then there is not children for current node
    private void insertValue(final Node node, final int value, final int position) {
        //noinspection ManualArrayCopy
        for (int i = node.length; i > position; i--) {
            node.values[i] = node.values[i - 1];
        }
        node.values[position] = value;
        node.length++;
    }

    // splits on two halves and returns new root node
    /*
                                              []3[]
                                             /     \
           []1[]2[]3[]4[]5[] --------> []1[]2[] | []4[]5[]

     */
    private Node split(final Node node) {
        final Node secondHalf = new Node();
        secondHalf.length = M / 2;
        node.length = M / 2;
        //noinspection ManualArrayCopy
        for (int i = secondHalf.length; i > 0; i--) {
            secondHalf.children[i] = node.children[M / 2 + i + 1];
            secondHalf.values[i - 1] = node.values[M / 2 + i];
        }
        secondHalf.children[0] = node.children[M / 2 + 1];
        final Node newRoot = new Node();
        newRoot.length = 1;
        newRoot.children[0] = node;
        newRoot.children[1] = secondHalf;
        newRoot.values[0] = node.values[M / 2];
        return newRoot;
    }

    public int size() {
        return size;
    }

    public int height() {
        return height;
    }

    public boolean contains(final int value) {
        Node node = root;
        int i = 0;
        while (true) {
            if (i == node.length || value < node.values[i]) {
                if (node.children[i] == null) {
                    break;
                } else {
                    node = node.children[i];
                    i = 0;
                }
            } else if (value == node.values[i]) {
                return true;
            } else {
                i++;
            }
        }
        return false;
    }
}
