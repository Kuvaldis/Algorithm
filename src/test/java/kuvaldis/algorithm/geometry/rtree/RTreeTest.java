package kuvaldis.algorithm.geometry.rtree;

import kuvaldis.algorithm.geometry.SquareArea;
import kuvaldis.algorithm.geometry.Point;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RTreeTest {

    @Test
    public void sanityCheck() throws Exception {
        // given
        final RTree rTree = new RTree(4, 2);

        // when
        rTree.add(new Point(3, 4));
        rTree.add(new Point(1, 7));
        rTree.add(new Point(7, 1));
        rTree.add(new Point(2, 2));
        rTree.add(new Point(5, 7));
        rTree.add(new Point(3, 6));
        rTree.add(new Point(6, 3));
        rTree.add(new Point(4, 6));
        rTree.add(new Point(6, 5));
        rTree.add(new Point(8, 1));
        rTree.add(new Point(3, 5));
        rTree.add(new Point(4, 5));
        rTree.add(new Point(1, 1));
        rTree.add(new Point(10, 2));
        rTree.add(new Point(2, 3));
        rTree.add(new Point(9, 5));
        rTree.add(new Point(2, 8));

        // then
        final List<Point> nonEmptyResult = rTree.search(new SquareArea(new Point(4, 3), new Point(9, 7)));
        assertThat(nonEmptyResult, Matchers.containsInAnyOrder(
                new Point(4, 5),
                new Point(4, 6),
                new Point(5, 7),
                new Point(6, 3),
                new Point(6, 5),
                new Point(9, 5)
        ));

        // and
        final List<Point> emptyResult = rTree.search(new SquareArea(new Point(7, 7), new Point(8, 8)));
        assertTrue(emptyResult.isEmpty());
    }
}