package kuvaldis.algorithm.sort;

public class SelectionSortBenchmark extends SortBenchmark {

    @Override
    protected Class<? extends Sort> instanceClass() {
        return SelectionSort.class;
    }
}