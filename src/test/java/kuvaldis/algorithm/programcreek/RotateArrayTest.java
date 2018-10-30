package kuvaldis.algorithm.programcreek;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RotateArrayTest {

    @Test
    public void rotate() {
        final int[] arr = new int[] {1, 2, 3, 4};
        final int k = 2;
        new RotateArray().rotateArray(arr, k);
        assertArrayEquals(new int[] {3, 4, 1, 2}, arr);
    }
}