package kuvaldis.algorithm.sort;

public abstract class Sort {

    public abstract int[] sort(final int[] input);

    protected void swap(final int[] array, final int i, final int j) {
        if (i == j) return;
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }
}
