package kuvaldis.algorithm.sort;

public class HeapSortBenchmark extends SortBenchmark {

    @Override
    protected Class<? extends Sort> instanceClass() {
        return HeapSort.class;
    }
}