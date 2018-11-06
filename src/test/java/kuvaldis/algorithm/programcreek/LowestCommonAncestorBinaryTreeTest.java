package kuvaldis.algorithm.programcreek;

import org.junit.Test;

import static kuvaldis.algorithm.programcreek.LowestCommonAncestorBinaryTree.*;
import static org.junit.Assert.*;

public class LowestCommonAncestorBinaryTreeTest {

    @Test
    public void testSimple() {
        final TreeNode binaryTree = new TreeNode(6,
                new TreeNode(3,
                        new TreeNode(2,
                                new TreeNode(1, null, null),
                                null),
                        new TreeNode(4, null, null)),
                new TreeNode(10,
                        null,
                        new TreeNode(15,
                                new TreeNode(13, null, null),
                                null)));
        assertEquals(3, new LowestCommonAncestorBinaryTree().search(binaryTree, 1, 4).intValue());
        assertEquals(2, new LowestCommonAncestorBinaryTree().search(binaryTree, 1, 2).intValue());
        assertEquals(6, new LowestCommonAncestorBinaryTree().search(binaryTree, 4, 15).intValue());
    }
}