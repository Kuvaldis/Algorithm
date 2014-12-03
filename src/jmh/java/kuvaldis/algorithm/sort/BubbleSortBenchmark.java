package kuvaldis.algorithm.sort;

public class BubbleSortBenchmark extends SortBenchmark {

    @Override
    protected Class<? extends Sort> instanceClass() {
        return BubbleSort.class;
    }
}