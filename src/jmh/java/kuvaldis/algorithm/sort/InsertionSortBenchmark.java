package kuvaldis.algorithm.sort;

public class InsertionSortBenchmark extends SortBenchmark {

    @Override
    protected void run(int[] input) {
        new InsertionSort().sort(input);
    }
}