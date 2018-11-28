package kuvaldis.algorithm.programcreek;

import kuvaldis.algorithm.programcreek.TreeNextRightPointers.TreeLinkNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeNextRightPointersTest {

    @Test
    public void simpleTest() {
        final TreeLinkNode root = new TreeLinkNode(1,
                new TreeLinkNode(2,
                        new TreeLinkNode(4,
                                new TreeLinkNode(8, null, null),
                                new TreeLinkNode(9, null, null)),
                        new TreeLinkNode(5,
                                new TreeLinkNode(10, null, null),
                                new TreeLinkNode(11, null, null))),
                new TreeLinkNode(3,
                        new TreeLinkNode(6,
                                new TreeLinkNode(12, null, null),
                                new TreeLinkNode(13, null, null)),
                        new TreeLinkNode(7,
                                new TreeLinkNode(14, null, null),
                                new TreeLinkNode(15, null, null))));

        new TreeNextRightPointers().populate(root);

        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15", asString(root));
    }

    private String asString(final TreeLinkNode root) {
        final StringBuilder sb = new StringBuilder();
        TreeLinkNode node = root;
        TreeLinkNode head = root;
        while (node != null) {
            sb.append(node.value)
                    .append(" ");
            if (node.next == null) {
                node = head.left;
                head = head.left;
            } else {
                node = node.next;
            }
        }
        return sb.toString().trim();
    }
}