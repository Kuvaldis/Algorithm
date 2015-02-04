package kuvaldis.algorithm.sort;

public class ShellSort extends Sort {
    @Override
    public int[] sort(int[] input) {
        final int[] result = new int[input.length];
        System.arraycopy(input, 0, result, 0, result.length);
        int gap = result.length >> 1;
        while (gap > 0) {
            for (int i = 0; i < result.length; i++) {
                for (int j = i; j >= gap && result[j] < result[j - gap]; j -= gap) {
                    swap(result, j, j - gap);
                }
            }
            gap = gap >> 1;
        }
        return result;
    }
}
