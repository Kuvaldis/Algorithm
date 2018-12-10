package kuvaldis.algorithm.programcreek;

import org.junit.Test;

import static org.junit.Assert.*;

public class HouseRobberIIITest {

    @Test
    public void simpleTest() {
        final TreeNode root = new TreeNode(10,
                new TreeNode(15,
                        new TreeNode(37, null, null),
                        null),
                new TreeNode(2,
                        new TreeNode(25,
                                new TreeNode(26, null, null),
                                null),
                        new TreeNode(11, null, null)));
        final int result = new HouseRobberIII().maxSum(root);
        assertEquals(47, result);
    }
}