package kuvaldis.algorithm.cake;

import kuvaldis.algorithm.cake.BinarySearchTreeChecker.BinaryTreeNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeCheckerTest {

    @Test
    public void testPositive() {
        // given
        final BinaryTreeNode tree = n(5,
                n(3,
                        n(1,
                                null,
                                null),
                        n(4,
                                null,
                                null)),
                n(8,
                        n(6,
                                null,
                                null),
                        n(10,
                                null,
                                null)));

        // when
        final String result = new BinarySearchTreeChecker().check(tree);

        // then
        assertEquals("", result);
    }

    @Test
    public void testNegative() {
        // given
        final BinaryTreeNode tree = n(5,
                n(3,
                        n(1,
                                null,
                                null),
                        n(6,
                                null,
                                null)),
                n(8,
                        n(6,
                                null,
                                null),
                        n(10,
                                null,
                                null)));

        // when
        final String result = new BinarySearchTreeChecker().check(tree);

        // then
        assertEquals("6", result);
    }

    private static BinaryTreeNode n(final int value, final BinaryTreeNode left, BinaryTreeNode right) {
        return new BinaryTreeNode(value, left, right);
    }
}