package kuvaldis.algorithm.programcreek;

import kuvaldis.algorithm.programcreek.FindLeavesOfBinaryTree.Node;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FindLeavesOfBinaryTreeTest {

    @Test
    public void testSimple() {
        final Node root = new Node(1,
                new Node(2,
                        new Node(4, null, null),
                        new Node(5, null, null)),
                new Node(3, null, null));
        final List<List<Integer>> result = new FindLeavesOfBinaryTree().find(root);
        final List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(4, 5, 3),
                Arrays.asList(2),
                Arrays.asList(1)
        );
        assertEquals(expected, result);
    }

    @Test
    public void testSlightlyMoreComplex() {
        final Node root = new Node(1,
                new Node(2,
                        new Node(4, null, null),
                        new Node(5, null, null)),
                new Node(3,
                        new Node(6,
                                new Node(7, null, null),
                                new Node(8,
                                        new Node(9, null, null),
                                        null)),
                        null));
        final List<List<Integer>> result = new FindLeavesOfBinaryTree().find(root);
        final List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(4, 5, 7, 9),
                Arrays.asList(2, 8),
                Arrays.asList(6),
                Arrays.asList(3),
                Arrays.asList(1)
        );
        assertEquals(expected, result);
    }
}