package kuvaldis.algorithm.structure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RedBlackTreeSet2Test {

    @Test
    public void testInsertDelete() throws Exception {
        final RedBlackTreeSet2 set = new RedBlackTreeSet2();
        assertEquals(0, set.size());

        set.insert(13);
        assertTrue(checkIsRedBlack(set));
        assertEquals(1, set.size());

        set.insert(2);
        assertTrue(checkIsRedBlack(set));
        assertEquals(2, set.size());

        set.insert(34);
        assertTrue(checkIsRedBlack(set));
        assertEquals(3, set.size());

        set.insert(36);
        assertTrue(checkIsRedBlack(set));
        assertEquals(4, set.size());

        set.insert(35);
        assertTrue(checkIsRedBlack(set));
        assertEquals(5, set.size());

        set.insert(4);
        assertTrue(checkIsRedBlack(set));
        assertEquals(6, set.size());

        set.insert(577);
        assertTrue(checkIsRedBlack(set));
        assertEquals(7, set.size());

        set.insert(576);
        assertTrue(checkIsRedBlack(set));
        assertEquals(8, set.size());

        set.delete(13);
        assertTrue(checkIsRedBlack(set));
        assertEquals(7, set.size());

        set.delete(13);
        assertTrue(checkIsRedBlack(set));
        assertEquals(7, set.size());

        set.delete(2);
        assertTrue(checkIsRedBlack(set));
        assertEquals(6, set.size());

        set.delete(577);
        assertTrue(checkIsRedBlack(set));
        assertEquals(5, set.size());

        set.delete(576);
        assertTrue(checkIsRedBlack(set));
        assertEquals(4, set.size());

        set.delete(34);
        assertTrue(checkIsRedBlack(set));
        assertEquals(3, set.size());

        set.delete(35);
        assertTrue(checkIsRedBlack(set));
        assertEquals(2, set.size());

        set.delete(36);
        assertTrue(checkIsRedBlack(set));
        assertEquals(1, set.size());

        set.delete(4);
        assertTrue(checkIsRedBlack(set));
        assertEquals(0, set.size());
    }

    private boolean checkIsRedBlack(final RedBlackTreeSet2 set) {
        //noinspection SimplifiableIfStatement
        if (set.root != null && set.root.color == RedBlackTreeSet2.Color.Red) {
            return false;
        }
        return checkSubtreeRedBlack(set.root) > 0;
    }

    private int checkSubtreeRedBlack(final RedBlackTreeSet2.Node node) {
        if (node == null) {
            return 1;
        }
        if (node.color == RedBlackTreeSet2.Color.Red) {
            if (node.left != null && node.left.color == RedBlackTreeSet2.Color.Red ||
                    node.right != null && node.right.color == RedBlackTreeSet2.Color.Red) {
                return -1;
            }
        }
        final int leftCount = checkSubtreeRedBlack(node.left);
        if (leftCount < 0) {
            return -1;
        }
        final int rightCount = checkSubtreeRedBlack(node.right);
        if (rightCount < 0) {
            return -1;
        }
        if (rightCount != leftCount) {
            return -1;
        }
        return (node.color == RedBlackTreeSet2.Color.Black ? 1 : 0) + leftCount;
    }

}