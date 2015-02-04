package kuvaldis.algorithm.sort;

public class QuickSortBenchmark extends SortBenchmark {

    @Override
    protected Class<? extends Sort> instanceClass() {
        return QuickSort.class;
    }
}