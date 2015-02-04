package kuvaldis.algorithm.sort;

public class ShellSortBenchmark extends SortBenchmark {

    @Override
    protected Class<? extends Sort> instanceClass() {
        return ShellSort.class;
    }
}