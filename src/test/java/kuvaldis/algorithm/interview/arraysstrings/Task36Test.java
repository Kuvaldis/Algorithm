package kuvaldis.algorithm.interview.arraysstrings;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.*;

public class Task36Test {

    @Test
    public void testSort() throws Exception {
        final Task36 t = new Task36();
        final Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(4);
        stack.push(1);
        stack.push(5);
        stack.push(6);
        stack.push(2);
        stack.push(2);
        stack.push(1);
        t.sort(stack);
        System.out.println(stack);
    }
}