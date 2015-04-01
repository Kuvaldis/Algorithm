package kuvaldis.algorithm.dynamic;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinomialCoefficientTest {

    final BinomialCoefficient bc = new BinomialCoefficient();

    @Test
    public void testCalculate() throws Exception {
        assertEquals(1l, c(0, 0));

        assertEquals(1l, c(1, 0));
        assertEquals(1l, c(1, 1));

        assertEquals(1l, c(2, 0));
        assertEquals(2l, c(2, 1));
        assertEquals(1l, c(2, 2));

        assertEquals(1l, c(3, 0));
        assertEquals(3l, c(3, 1));
        assertEquals(3l, c(3, 2));
        assertEquals(1l, c(3, 3));

        assertEquals(1l, c(4, 0));
        assertEquals(4l, c(4, 1));
        assertEquals(6l, c(4, 2));
        assertEquals(4l, c(4, 3));
        assertEquals(1l, c(4, 4));
    }

    private long c(final int n, final int m) {
        return bc.calculate(n, m);
    }
}