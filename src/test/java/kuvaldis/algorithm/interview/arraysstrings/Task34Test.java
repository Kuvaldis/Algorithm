package kuvaldis.algorithm.interview.arraysstrings;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.*;

public class Task34Test {

    @Test
    public void testTowerMove() throws Exception {
        final Task34 t = new Task34();
        final Deque<Integer> firstTower = new ArrayDeque<>();
        firstTower.push(5);
        firstTower.push(4);
        firstTower.push(3);
        firstTower.push(2);
        firstTower.push(1);
        final Deque<Integer> secondTower = new ArrayDeque<>();
        final Deque<Integer> thirdTower = new ArrayDeque<>();
        //noinspection unchecked
        final Deque<Integer>[] towers = new Deque[]{firstTower, secondTower, thirdTower};
        t.towerMove(towers, 5);
        assertEquals(1, thirdTower.pop().intValue());
        assertEquals(2, thirdTower.pop().intValue());
        assertEquals(3, thirdTower.pop().intValue());
        assertEquals(4, thirdTower.pop().intValue());
        assertEquals(5, thirdTower.pop().intValue());
    }
}