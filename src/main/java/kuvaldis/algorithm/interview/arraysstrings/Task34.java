package kuvaldis.algorithm.interview.arraysstrings;

import java.util.Deque;
import java.util.Stack;

public class Task34 {

    public void towerMove(final Deque<Integer>[] towers, final Integer n) {
        doMove(towers, n, 0, 2);
    }

    private void doMove(final Deque<Integer>[] towers, final Integer n, final int from, final int to) {
        if (n == 0) {
            return;
        }
        final int tempStackNum = 3 - from - to;
        doMove(towers, n - 1, from, tempStackNum);
        final Integer val = towers[from].pop();
        final Integer toVal = towers[to].peek();
        if (toVal != null && val > toVal) {
            throw new RuntimeException("Incorrect operation");
        }
        towers[to].push(val);
        doMove(towers, n - 1, tempStackNum, to);
    }
}
