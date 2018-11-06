package kuvaldis.algorithm.programcreek;

import kuvaldis.algorithm.programcreek.KSmallestElementBST.TreeNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class KSmallestElementBSTTest {

    @Test
    public void testSimple() {
        final TreeNode bst = new TreeNode(6,
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
        assertEquals(1, new KSmallestElementBST().search(bst, 1).intValue());
        assertEquals(2, new KSmallestElementBST().search(bst, 2).intValue());
        assertEquals(3, new KSmallestElementBST().search(bst, 3).intValue());
        assertEquals(4, new KSmallestElementBST().search(bst, 4).intValue());
        assertEquals(6, new KSmallestElementBST().search(bst, 5).intValue());
        assertEquals(10, new KSmallestElementBST().search(bst, 6).intValue());
        assertEquals(13, new KSmallestElementBST().search(bst, 7).intValue());
        assertEquals(15, new KSmallestElementBST().search(bst, 8).intValue());
    }
}