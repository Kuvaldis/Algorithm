package kuvaldis.algorithm.dynamic.fibonacci;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciTest {

    @Test
    public void testSimple() throws Exception {
        check(FibonacciSimple.class);
    }

    @Test
    public void testCache() throws Exception {
        check(FibonacciCache.class);
    }

    @Test
    public void testDynamic() throws Exception {
        check(FibonacciDynamic.class);
    }

    @Test
    public void testUltimate() throws Exception {
        check(FibonacciUltimate.class);
    }

    private <T extends Fibonacci> void check(final Class<T> fibonacciClass) throws IllegalAccessException, InstantiationException {
        assertEquals(0l, fibonacciClass.newInstance().get(0).longValue());
        assertEquals(1l, fibonacciClass.newInstance().get(1).longValue());
        assertEquals(1l, fibonacciClass.newInstance().get(2).longValue());
        assertEquals(2l, fibonacciClass.newInstance().get(3).longValue());
        assertEquals(3l, fibonacciClass.newInstance().get(4).longValue());
        assertEquals(5l, fibonacciClass.newInstance().get(5).longValue());
        assertEquals(8l, fibonacciClass.newInstance().get(6).longValue());
    }
}