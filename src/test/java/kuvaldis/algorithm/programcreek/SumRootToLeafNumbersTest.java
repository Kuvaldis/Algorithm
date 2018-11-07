package kuvaldis.algorithm.programcreek;

import org.junit.Test;

import static org.junit.Assert.*;

public class SumRootToLeafNumbersTest {

    @Test
    public void simpleTest() {
        final TreeNode root = new TreeNode(1,
                new TreeNode(2, null, null),
                new TreeNode(3, null, null));
        assertEquals(25, new SumRootToLeafNumbers().sum(root));
    }

    @Test
    public void simpleTest2() {
        final TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(0, null, null),
                        new TreeNode(9, null, null)),
                new TreeNode(3,
                        null,
                        new TreeNode(5,
                                null,
                                new TreeNode(6, null, null))));
        assertEquals(1605, new SumRootToLeafNumbers().sum(root));
    }
}