package kuvaldis.algorithm.sort;

import java.util.stream.IntStream;

public abstract class Sort {

    public abstract int[] sort(final int[] input);

    protected void swap(final int[] array, final int i, final int j) {
        if (i == j) return;
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }

    IntStream range(final int from, final int to) {
        return IntStream.range(from, to);
    }

    IntStream reverseRange(final int from, final int to) {
        return IntStream.range(to, from).map(i -> from + to - i);
    }
}
