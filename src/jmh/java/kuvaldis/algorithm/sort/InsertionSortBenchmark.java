package kuvaldis.algorithm.sort;

public class InsertionSortBenchmark extends SortBenchmark {

    @Override
    protected Class<? extends Sort> instanceClass() {
        return InsertionSort.class;
    }
}