package kuvaldis.algorithm.programcreek;

import kuvaldis.algorithm.programcreek.BinaryTreeLongestConsecutiveSequence.Node;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeLongestConsecutiveSequenceTest {

    @Test
    public void simpleTest() {
        final Node root = new Node(1,
                new Node(2,
                        new Node(1,
                                null,
                                new Node(2,
                                        null,
                                        new Node(3, null, null))),
                        new Node(3,
                                null,
                                new Node(4,
                                        null, new Node(5, null, null)))),
                new Node(6,
                        null,
                        new Node(7,
                                null,
                                new Node(8,
                                        null,
                                        new Node(9,
                                                new Node(10, null, null),
                                                null)))));
        assertEquals(5, new BinaryTreeLongestConsecutiveSequence().find(root));
    }
}