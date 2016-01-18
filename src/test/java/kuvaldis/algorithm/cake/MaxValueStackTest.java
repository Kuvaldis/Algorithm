package kuvaldis.algorithm.cake;

import org.junit.Test;

import static org.junit.Assert.*;

/*

You want to be able to access the largest element in a stack.
Use the built-in Stack class to implement a new class MaxStack with a function getMax() that returns the largest element in the stack. getMax() should not remove the item.

Your stacks will contain only integers.

 */
public class MaxValueStackTest {
    @Test
    public void testMaxValueStack() throws Exception {
        final MaxValueStack stack = new MaxValueStack();
        stack.push(1);
        assertEquals(1, stack.getMax());
        stack.push(2);
        assertEquals(2, stack.getMax());
        stack.pop();
        assertEquals(1, stack.getMax());
        stack.push(3);
        assertEquals(3, stack.getMax());
        stack.push(-1);
        assertEquals(3, stack.getMax());
        stack.pop();
        assertEquals(3, stack.getMax());
        stack.push(3);
        assertEquals(3, stack.getMax());
        stack.pop();
        assertEquals(3, stack.getMax());
        stack.pop();
        assertEquals(1, stack.getMax());
    }
}