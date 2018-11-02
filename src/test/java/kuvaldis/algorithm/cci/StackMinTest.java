package kuvaldis.algorithm.cci;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackMinTest {

    @Test
    public void simpleTest() {
        final StackMin stack = new StackMin(10);
        stack.push(5);
        assertEquals(5, stack.min());
        stack.push(7);
        assertEquals(5, stack.min());
        stack.push(3);
        assertEquals(3, stack.min());
        stack.push(8);
        assertEquals(3, stack.min());
        stack.push(2);
        assertEquals(2, stack.min());
        stack.push(5);
        assertEquals(2, stack.min());
        stack.push(1);
        assertEquals(1, stack.min());
        stack.pop();
        assertEquals(2, stack.min());
        stack.pop();
        assertEquals(2, stack.min());
        stack.pop();
        assertEquals(3, stack.min());
        stack.pop();
        assertEquals(3, stack.min());
        stack.pop();
        assertEquals(5, stack.min());
        stack.pop();
        assertEquals(5, stack.min());
    }
}