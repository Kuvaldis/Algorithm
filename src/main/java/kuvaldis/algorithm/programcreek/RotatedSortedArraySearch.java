package kuvaldis.algorithm.programcreek;

public class RotatedSortedArraySearch {

    public int search(final int[] arr, final int value) {
        if (arr == null) {
            return -1;
        }
        int leftIndex = 0;
        int rightIndex = arr.length - 1;
        int middleIndex;
        while (leftIndex < rightIndex) {
            middleIndex = (rightIndex + leftIndex) / 2;
            if (value == arr[middleIndex]) {
                return middleIndex;
            }
            if (arr[middleIndex] > arr[rightIndex]) {
                if (arr[leftIndex] <= value && value < arr[middleIndex]) {
                    rightIndex = middleIndex - 1;
                } else {
                    leftIndex = middleIndex + 1;
                }
            } else {
                // rotation point is either to the left or not within interval
                if (arr[middleIndex] < value && value <= arr[rightIndex]) {
                    leftIndex = middleIndex + 1;
                } else {
                    rightIndex = middleIndex - 1;
                }
            }
        }
        if (leftIndex == rightIndex && arr[leftIndex] == value) {
            return leftIndex;
        }
        return -1;
    }
}
