package kuvaldis.algorithm.programcreek;

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
