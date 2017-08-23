package kuvaldis.algorithm.structure;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Set implementation based on skip list. Only unique values are allowed.
 */
public class SkipListSet {

    /**
     * Top level list head
     */
    private Head top;

    /**
     * Bottom level list head
     */
    private Head bottom;

    /**
     * How many levels are currently present
     */
    private int levels;

    /**
     * Number of elements on the bottom level
     */
    private int size;

    public SkipListSet() {
        this.bottom = this.top = new Head(null, null);
        this.levels = 1;
    }

    /**
     * List head node, one for each level.
     */
    private static final class Head {
        private Node right;
        private Head up;

        Head(final Node right, final Head up) {
            this.right = right;
            this.up = up;
        }
    }

    /**
     * List node, containing value
     */
    private static final class Node {

        private int value;

        private Node right;
        private Node down;

        public Node(final int value, final Node right, final Node down) {
            this.value = value;
            this.right = right;
            this.down = down;
        }
    }

    public boolean add(final int value) {
        final Node[] predecessors = findPredecessors(value);
        final Node lastPredecessor = predecessors[predecessors.length - 1];

        if (lastPredecessor == null) {
            // in this case we move from bottom to top predecessors.
            // with 1/2 probability we decide whether the former first value should be present on the next level or not.
            // if in some level we decide that it should not be it is just substituted by a new value with links updates.

            // first element should always be present
            // Top:    [H] -> ...                       Top:    [H] -> ...
            // ....                                 =>  ....
            // Bottom: [H] -> [next]                    Bottom: [H] -> [newNode] -> [next]
            //               ^
            //               Insert new node here
            Node next = bottom.right;
            Node newNode = new Node(value, next, null);
            bottom.right = newNode;
            // insert to other levels.
            // with probability of 1/2 a current first value is left intact.
            Head head = bottom.up;
            int i = predecessors.length - 2;
            for (; i >= 0; i--) {
                if (flipCoin()) {
                    // leave previous first value as is, only insert the new one before it

                    // Top:    [H] -> ...                       Top:    [H] -> ...
                    // ....                                 =>  ....
                    //         [H] -> [next]                            [H] -> [newNode] -> [next]
                    // ....          ^                          ....               ||         ||
                    // ....          Insert new node here       ....               \/         \/
                    // Bottom: [H] -> ...                       Bottom: [H] -> [newNode] -> [next]
                    next = head.right;
                    newNode = new Node(value, next, newNode);
                    head.right = newNode;

                    head = head.up;
                } else {
                    // in this case from the current level to top we just substitute values and update links
                    break;
                }
            }
            // if flip coin returned false and there are levels to insert new value left we just replace first values in level and update links
            // Top:    [H] -> ...                                      Top:  [H] -> ...
            // ....                                                 => ....
            //         [H] ->              [currentValue] -> [next]          [H] -> [newValue]          ->          [next]
            //                                  ||             ||                       ||                            ||
            //                                  \/             \/                       \/                            \/
            //         [H] -> [newNode] -> [currentNode]  -> [next]          [H] -> [newNode]  -> [currentNode]  -> [next]
            // update link only one time, in other cases the links to down node should be the same, only values are required to be updated
            if (i >= 0) {
                head.right.down = newNode;
            }
            for (; i >= 0; i--) {
                final Node current = head.right;
                current.value = value;
                head = head.up;
            }
        } else if (lastPredecessor.right == null || lastPredecessor.right.value != value) {
            // first, add to the bottom layer
            // Top:    [H] -> ...                                Top:    [H] -> ...
            // ....                                          =>  ....
            // Bottom: [H] -> ... -> [prev] -> [next] -> ...     Bottom: [H] -> ... -> [prev] -> [newNode] -> [next]
            //                              ^
            //                              Insert new node here
            Node prev = predecessors[predecessors.length - 1];
            Node newNode = new Node(value, prev.right, null);
            prev.right = newNode;

            // insert to other levels with 1/2 probability
            int i = predecessors.length - 2;
            for (; i >= 0; i--) {

                if (flipCoin()) {
                    // Top: [H] -> ...                                    Top: [H] -> ...
                    // ....                                           =>  ....
                    //      [H] -> ... -> [prev] -> [next] -> ...              [H] -> ... -> [prev] -> [newNode] -> [next] -> ...
                    // ....                 ||   ^                        ....                 ||         ||          ||
                    // ....                 \/   Insert new node here     ....                 \/         \/          \/
                    //      [H] -> ...                                         [H] -> ... -> [prev] -> [newNode] -> [next] -> ...
                    prev = predecessors[i];
                    newNode = new Node(value, prev.right, newNode);
                    prev.right = newNode;
                } else {
                    // in this case we just skip other levels, so a new node is not inserted there
                    break;
                }
            }
        } else {
            // value already exists
            return false;
        }

        // the node is inserted in this place, size should be increased
        size++;

        // check another top level is needed and add one if so
        if (Math.pow(2, levels) == size) {
            // in this case we should add a new level with the first element
            //                                        Top: [newTop] -> [newLevelFirst] -> ...
            //                                     =>         /\              ||
            //                                                ||              \/
            // Top: [currentTop] -> [first] -> ...         [oldTop] ->     [first]     -> ...
            // ....                                   ....
            final Node first = top.right;
            final Node newLevelFirst = new Node(first.value, null, first);
            final Head newTop = new Head(newLevelFirst, null);
            top.up = newTop;
            top = newTop;
            levels++;
        }
        return true;
    }

    public int[] toArray() {
        final int[] result = new int[size];
        Node n = bottom.right;
        for ( int i = 0; i < size; i++) {
            result[i] = n.value;
            n = n.right;
        }
        return result;
    }

    /**
     * Returns predecessors from top level to bottom. Null means it should be inserted in the beginning.
     */
    private Node[] findPredecessors(final int value) {
        Head h = top;
        Node n = h.right;
        final Node[] predecessors = new Node[levels];

        // in case it is less than first element
        if (n == null || value < n.value) {
            return predecessors;
        }

        int level = 0;
        while (true) {
            if (n == null) {
                return predecessors;
            }

            final Node next = n.right;
            if (next == null) {
                predecessors[level] = n;
                n = n.down;
                level++;
                continue;
            }

            // try next level, to insert in exact place
            if (value <= next.value) {
                predecessors[level] = n;
                n = n.down;
                level++;
                continue;
            }

            // value is greater than next node value, move to right
            n = n.right;
        }
    }

    private boolean flipCoin() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
