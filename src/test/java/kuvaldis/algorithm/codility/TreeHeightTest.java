package kuvaldis.algorithm.codility;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreeHeightTest {

    @Test
    public void testSolution() throws Exception {
        assertEquals(2, new TreeHeight().solution(tree(5, tree(3, tree(20, null, null),
                tree(21, null, null)), tree(10, tree(1, null, null), null))));
    }

    private static TreeHeight.Tree tree(final int x, final TreeHeight.Tree l, final TreeHeight.Tree r) {
        final TreeHeight.Tree tree = new TreeHeight.Tree();
        tree.x = x;
        tree.l = l;
        tree.r = r;
        return tree;
    }
}