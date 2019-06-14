package kuvaldis.algorithm.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class DistributeCoinsInBinaryTreeTest {

    @Test
    public void testExample1() {
        // given
        final TreeNode tree = new TreeNode(3,
                new TreeNode(0, null, null),
                new TreeNode(0, null, null));

        // when
        final int moves = new DistributeCoinsInBinaryTree().distribute(tree);

        // then
        assertEquals(2, moves);
    }

    @Test
    public void testExample2() {
        // given
        final TreeNode tree = new TreeNode(0,
                new TreeNode(3, null, null),
                new TreeNode(0, null, null));

        // when
        final int moves = new DistributeCoinsInBinaryTree().distribute(tree);

        // then
        assertEquals(3, moves);
    }

    @Test
    public void testExample3() {
        // given
        final TreeNode tree = new TreeNode(1,
                new TreeNode(0, null, null),
                new TreeNode(2, null, null));

        // when
        final int moves = new DistributeCoinsInBinaryTree().distribute(tree);

        // then
        assertEquals(2, moves);
    }

    @Test
    public void testExample4() {
        // given
        final TreeNode tree = new TreeNode(1,
                new TreeNode(0,
                        null,
                        new TreeNode(3, null, null)),
                new TreeNode(0, null, null));

        // when
        final int moves = new DistributeCoinsInBinaryTree().distribute(tree);

        // then
        assertEquals(4, moves);
    }

    @Test
    public void testExample5() {
        // given
        final TreeNode tree = new TreeNode(0,
                new TreeNode(0,
                        new TreeNode(0, null, null),
                        new TreeNode(0, null, null)),
                new TreeNode(5, null, null));

        // when
        final int moves = new DistributeCoinsInBinaryTree().distribute(tree);

        // then
        assertEquals(9, moves);
    }

    @Test
    public void testExample6() {
        // given
        final TreeNode tree = new TreeNode(1,
                new TreeNode(0,
                        new TreeNode(0, null, null),
                        new TreeNode(4, null, null)),
                new TreeNode(0, null, null));

        // when
        final int moves = new DistributeCoinsInBinaryTree().distribute(tree);

        // then
        assertEquals(6, moves);
    }

    @Test
    public void testExample7() {
        // given
        final TreeNode tree = new TreeNode(2,
                new TreeNode(1,
                        new TreeNode(0,
                                new TreeNode(2, null, null),
                                null),
                        null),
                new TreeNode(0, null, null));

        // when
        final int moves = new DistributeCoinsInBinaryTree().distribute(tree);

        // then
        assertEquals(2, moves);
    }
}