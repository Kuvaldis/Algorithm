package kuvaldis.algorithm.sort;

import java.util.stream.IntStream;

public class InsertionSort extends Sort {

    @Override
    public int[] sort(final int[] input) {
        final int[] output = new int[input.length];
        System.arraycopy(input, 0, output, 0, output.length);
        IntStream.range(1, output.length).forEach(i -> {
            int key = output[i];
            int j = i;
            while (j > 0 && output[j - 1] > key) {
                output[j] = output[j - 1];
                j--;
            }
            output[j] = key;
        });
        return output;
    }
}
