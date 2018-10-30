package kuvaldis.algorithm.programcreek;

/*

Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. How many different ways do you know to solve this problem?

 */
public class RotateArray {

    public void rotateArray(final int[] arr, final int k) {
        int start = 0;
        int changed = arr.length;
        int el;
        while (changed != 0) {
            int next = start;
            el = arr[start];
            do {
                next = (next + k) % arr.length;
                el ^= arr[next];
                arr[next] ^= el;
                el ^= arr[next];
                changed--;
            } while (next != start);
            start++;
        }
    }
}
