package kuvaldis.algorithm.sort;

import java.util.stream.IntStream;

public class SelectionSort extends Sort {

    @Override
    public int[] sort(final int[] input) {
        final int[] output = new int[input.length];
        System.arraycopy(input, 0, output, 0, output.length);
        IntStream.range(0, output.length - 1).forEach(i -> {
            final int[] indexMin = {i};
            IntStream.range(i + 1, output.length).forEach(j -> {
                if (output[j] < output[indexMin[0]]) {
                    indexMin[0] = j;
                }
            });
            swap(output, i, indexMin[0]);
        });
        return output;
    }
}
