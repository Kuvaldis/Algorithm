package kuvaldis.algorithm.sort;

public class SelectionSortBenchmark extends SortBenchmark {

    @Override
    protected Class<? extends Sort> instance() {
        return SelectionSort.class;
    }
}