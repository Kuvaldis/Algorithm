package kuvaldis.algorithm.cake;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task2Test {

    @Test
    public void testGetProductsOfAllIntsExceptAtIndex() throws Exception {
        final Task2 task2 = new Task2();
        final int[] numbers = {1, 7, 3, 4};
        final int[] answer = {84, 12, 28, 21};
        assertArrayEquals(answer, task2.getProductsOfAllIntsExceptAtIndex(numbers));
    }
}