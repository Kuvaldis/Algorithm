package kuvaldis.algorithm.cake;

/*

You have an array of integers, and for each index you want to find the product of every integer except the integer at that index.
Write a function getProductsOfAllIntsExceptAtIndex() that takes an array of integers and returns an array of the products.

 */
public class Task2 {

    public int[] getProductsOfAllIntsExceptAtIndex(final int[] numbers) {
        if (numbers.length < 2) {
            return null;
        }
        final int[] result = new int[numbers.length];
        result[0] = 1;
        for (int i = 1; i < numbers.length; i++) {
            result[i] = result[i - 1] * numbers[i - 1];
        }
        int multi = 1;
        for (int i = numbers.length - 1; i >= 0; i--) {
            result[i] *= multi;
            multi *= numbers[i];
        }
        return result;
    }

}
