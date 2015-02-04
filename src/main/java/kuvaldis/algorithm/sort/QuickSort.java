package kuvaldis.algorithm.sort;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends Sort {

    @Override
    public int[] sort(int[] input) {
        final int[] result = new int[input.length];
        System.arraycopy(input, 0, result, 0, result.length);
        quickSort(result, 0, result.length);
        return result;
    }

    private void quickSort(final int[] array, final int from, final int to) {
        if (from < to) {
            final int p = partition(array, from, to);
            quickSort(array, from, p);
            quickSort(array, p + 1, to);
        }
    }

    private int partition(final int[] array, final int from, final int to) {
        int p = pickPivot(from, to);
        swap(array, p, from);
        p = from;
        int k = from;
        for (int i = p + 1; i < to; i++) {
            if (array[i] < array[p]) {
                swap(array, i, ++k);
            }

        }
        swap(array, p, k);
        return k;
    }

    private int pickPivot(final int from, final int to) {
        final int length = to - from;
        return ThreadLocalRandom.current().nextInt(from + length / 4, from + 3 * length / 4 + 1);
    }
}
