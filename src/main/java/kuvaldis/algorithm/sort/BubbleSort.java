package kuvaldis.algorithm.sort;

import java.util.stream.IntStream;

public class BubbleSort extends Sort {
    @Override
    public int[] sort(int[] input) {
        final int[] output = new int[input.length];
        System.arraycopy(input, 0, output, 0, output.length);
        range(0, output.length).forEach(i -> {
            reverseRange(output.length - 1, i).forEach(j -> {
                if (output[j] < output[j - 1]) {
                    swap(output, j, j - 1);
                }
            });
        });
        return output;
    }
}
