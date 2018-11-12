package kuvaldis.algorithm.programcreek;

import org.junit.Test;

import static org.junit.Assert.*;

public class EditDistanceTest {

    @Test
    public void testSimple() {
        assertEquals(0, new EditDistance().distance("a", "a"));
        assertEquals(1, new EditDistance().distance("a", "b"));
        assertEquals(3, new EditDistance().distance("abc", "def"));
        assertEquals(2, new EditDistance().distance("abc", "abde"));
    }
}