package kuvaldis.algorithm.sort;

public class MergeSortBenchmark extends SortBenchmark {

    @Override
    protected Class<? extends Sort> instanceClass() {
        return MergeSort.class;
    }
}