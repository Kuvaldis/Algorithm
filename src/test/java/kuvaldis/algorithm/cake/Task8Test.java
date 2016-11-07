package kuvaldis.algorithm.cake;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task8Test {

    @Test
    public void testIsSuperbalanced() throws Exception {
        assertTrue(new Task8().isSuperbalanced(tree(null, null)));
        assertTrue(new Task8().isSuperbalanced(tree(tree(tree(null, null), null), null)));
        assertTrue(new Task8().isSuperbalanced(tree(tree(tree(null, null), null), tree(null, null))));
        assertTrue(new Task8().isSuperbalanced(tree(tree(tree(tree(null, null), null), tree(null, null)), tree(tree(null, null), null))));
        assertFalse(new Task8().isSuperbalanced(tree(tree(tree(tree(tree(null, null), null), null), tree(null, null)), tree(tree(null, null), null))));
        assertFalse(new Task8().isSuperbalanced(tree(tree(tree(tree(null, null), null), null), tree(null, null))));
    }

    private static Task8.Tree tree(final Task8.Tree left, final Task8.Tree right) {
        return new Task8.Tree(left, right);
    }
}