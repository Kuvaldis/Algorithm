package kuvaldis.algorithm.sort;

public class RadixSortBenchmark extends SortBenchmark {

    @Override
    protected Class<? extends Sort> instanceClass() {
        return RadixSort.class;
    }
}