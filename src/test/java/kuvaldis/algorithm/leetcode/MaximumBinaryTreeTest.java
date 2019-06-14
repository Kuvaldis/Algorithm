package kuvaldis.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaximumBinaryTreeTest {

    @Test
    public void testSimple() {
        // given
        final int[] arr = new int[]{3, 2, 1, 6, 0, 5};

        // when
        final TreeNode root = new MaximumBinaryTree().buildTree(arr);

        // then
        assertEquals(6, root.value);
        assertEquals(3, root.left.value);
        assertEquals(2, root.left.right.value);
        assertEquals(1, root.left.right.right.value);
        assertEquals(5, root.right.value);
        assertEquals(0, root.right.left.value);
    }

    @Test
    public void testSortedAsc() {
        // given
        final int[] arr = new int[]{1, 2, 3, 4, 5};

        // when
        final TreeNode root = new MaximumBinaryTree().buildTree(arr);

        // then
        assertEquals(5, root.value);
        assertEquals(4, root.left.value);
        assertEquals(3, root.left.left.value);
        assertEquals(2, root.left.left.left.value);
        assertEquals(1, root.left.left.left.left.value);
    }

    @Test
    public void testSortedDesc() {
        // given
        final int[] arr = new int[]{5, 4, 3, 2, 1};

        // when
        final TreeNode root = new MaximumBinaryTree().buildTree(arr);

        // then
        assertEquals(5, root.value);
        assertEquals(4, root.right.value);
        assertEquals(3, root.right.right.value);
        assertEquals(2, root.right.right.right.value);
        assertEquals(1, root.right.right.right.right.value);
    }
}