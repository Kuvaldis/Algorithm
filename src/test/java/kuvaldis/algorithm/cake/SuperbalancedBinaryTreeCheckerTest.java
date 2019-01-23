package kuvaldis.algorithm.cake;

import kuvaldis.algorithm.cake.SuperbalancedBinaryTreeChecker.BinaryTreeNode;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class SuperbalancedBinaryTreeCheckerTest {

    @Test
    public void testSimple() {
        final BinaryTreeNode tree = new BinaryTreeNode(1,
                new BinaryTreeNode(2,
                        new BinaryTreeNode(3, null, null),
                        new BinaryTreeNode(4,
                                null,
                                new BinaryTreeNode(5,
                                        new BinaryTreeNode(6, null, null),
                                        null))),
                null);
        assertFalse(new SuperbalancedBinaryTreeChecker().isSuperbalanced(tree));
    }
}