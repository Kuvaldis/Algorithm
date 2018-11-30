package kuvaldis.algorithm.programcreek;

import org.junit.Test;

import static org.junit.Assert.*;

public class UniqueBSTTest {

    @Test
    public void simpleTest() {
        assertEquals(1, new UniqueBST().calculate(1));
        assertEquals(2, new UniqueBST().calculate(2));
        assertEquals(5, new UniqueBST().calculate(3));
        assertEquals(14, new UniqueBST().calculate(4));
    }
}