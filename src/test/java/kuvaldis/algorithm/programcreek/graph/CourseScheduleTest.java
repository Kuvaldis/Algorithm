package kuvaldis.algorithm.programcreek.graph;

import kuvaldis.algorithm.programcreek.graph.Graph.Node;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseScheduleTest {

    @Test
    public void testPositive() {
        final Graph graph = new Graph();
        final Node n0 = new Node(0);
        final Node n1 = new Node(1);
        final Node n2 = new Node(2);
        final Node n3 = new Node(3);
        final Node n4 = new Node(4);
        n0.setNeighbours(n1);
        n1.setNeighbours(n2, n3);
        n2.setNeighbours(n4);
        n3.setNeighbours(n4);
        graph.setNodes(n0, n1, n2, n3, n4);
        assertTrue(new CourseSchedule().isValid(graph));
    }

    @Test
    public void testNegative() {
        final Graph graph = new Graph();
        final Node n0 = new Node(0);
        final Node n1 = new Node(1);
        final Node n2 = new Node(2);
        final Node n3 = new Node(3);
        final Node n4 = new Node(4);
        n0.setNeighbours(n1);
        n1.setNeighbours(n2, n3);
        n2.setNeighbours(n4);
        n3.setNeighbours(n4);
        n4.setNeighbours(n0);
        graph.setNodes(n0, n1, n2, n3, n4);
        assertFalse(new CourseSchedule().isValid(graph));
    }
}