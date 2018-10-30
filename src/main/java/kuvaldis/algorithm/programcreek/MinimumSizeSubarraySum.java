package kuvaldis.algorithm.programcreek;

/*

Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum >= s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length of 2 under the problem constraint.

 */
public class MinimumSizeSubarraySum {

    public int findLength(final int[] arr, final int s) {
        if (arr == null) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int sum = 0;
        while (end < arr.length) {
            sum += arr[end++];
            // try to remove from the start
            while (sum - arr[start] >= s) {
                sum -= arr[start++];
            }    
        }
        if (sum < s) {
            return 0;
        }
        return end - start;
    }
}
