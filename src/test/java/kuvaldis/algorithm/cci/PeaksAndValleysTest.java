package kuvaldis.algorithm.cci;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PeaksAndValleysTest {

    @Test
    public void testSimple() {
        final int[] arr = new int[]{5, 8, 6, 2, 3, 4, 6};
        new PeaksAndValleys().fix(arr);
        System.out.println(Arrays.toString(arr));
    }
}