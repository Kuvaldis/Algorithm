package kuvaldis.algorithm.programcreek;

import kuvaldis.algorithm.programcreek.BinaryTreeRightSideView.Node;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BinaryTreeRightSideViewTest {

    @Test
    public void testSimple() {
        final Node root = new Node(1,
                new Node(2,
                        null,
                        new Node(5, null, null)),
                new Node(3, null, null));
        final List<Integer> result = new BinaryTreeRightSideView().getRightSideView(root);
        assertEquals(Arrays.asList(1, 3, 5), result);
    }

    @Test
    public void testSimple2() {
        final Node root = new Node(1,
                new Node(2,
                        null,
                        new Node(4,
                                new Node(6, null, null),
                                null)),
                new Node(3,
                        new Node(5, null, null),
                        null));
        final List<Integer> result = new BinaryTreeRightSideView().getRightSideView(root);
        assertEquals(Arrays.asList(1, 3, 5, 6), result);
    }
}