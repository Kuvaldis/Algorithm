package kuvaldis.algorithm.cci;

public class PeaksAndValleys {

    public void fix(final int[] arr) {
        if (arr == null || arr.length < 3) {
            return;
        }

        boolean isExpectingPeak = arr[0] >= arr[1];
        for (int i = 1; i < arr.length - 1; i++) {
            isExpectingPeak = !isExpectingPeak;
            final boolean isCurrentPeak = arr[i] >= arr[i + 1];
            if (isExpectingPeak != isCurrentPeak) {
                arr[i] ^= arr[i + 1];
                arr[i + 1] ^= arr[i];
                arr[i] ^= arr[i + 1];
            }
        }
    }
}
