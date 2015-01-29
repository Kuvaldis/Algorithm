package kuvaldis.algorithm.sort;

public class MergeSort extends Sort {

    // save original array
    @Override
    public int[] sort(final int[] input) {
        if (input.length <= 1) {
            return input;
        }
        int[] left = new int[input.length / 2];
        int[] right = new int[input.length - left.length];
        System.arraycopy(input, 0, left, 0, left.length);
        System.arraycopy(input, left.length, right, 0, right.length);
        left = sort(left);
        right = sort(right);
        return merge(left, right);
    }

    private int[] merge(final int[] leftPart, final int[] rightPart) {
        final int[] result = new int[leftPart.length + rightPart.length];
        int i = 0, j = 0, k = 0;
        while (i < leftPart.length && j < rightPart.length) {
            if (leftPart[i] <= rightPart[j]) {
                result[k++] = leftPart[i++];
            } else {
                result[k++] = rightPart[j++];
            }
        }
        if (i == leftPart.length) {
            System.arraycopy(rightPart, j, result, k, rightPart.length - j);
        }
        if (j == rightPart.length) {
            System.arraycopy(leftPart, i, result, k, leftPart.length - i);
        }
        return result;
    }
}
