package kuvaldis.algorithm.cake;

/*

I have an array where every number in the range 1...n appears once except for one number which appears twice.
Write a function for finding the number that appears twice.

 */
public class WhichAppearsTwice {

    public int solution(final int[] a) {
        // the length of the array is equal n + 1 since 1..n includes n values, but in our array there is one more.
        // sum from 1 to n. includes all from 1..n
        final long n = a.length - 1;
        final long sum1n = (n * (n + 1)) / 2;
        long sum = 0;
        // includes all the values in the array
        for (int value : a) {
            sum += value;
        }
        // subtraction is the searched number
        return (int) (sum - sum1n);
    }
}
