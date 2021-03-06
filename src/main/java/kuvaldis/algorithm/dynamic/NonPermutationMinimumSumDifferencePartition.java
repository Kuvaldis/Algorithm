package kuvaldis.algorithm.dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Splits the given list on n partitions with minimum difference between sum of elements in each partition
 */
class NonPermutationMinimumSumDifferencePartition {

    public class Partition {
        private final int startInclusive;
        private final int endInclusive;
        private final List<Integer> values;

        public Partition(final int startInclusive, final int endInclusive, final List<Integer> values) {
            this.startInclusive = startInclusive;
            this.endInclusive = endInclusive;
            this.values = values;
        }

        public int getStartInclusive() {
            return startInclusive;
        }

        public int getEndInclusive() {
            return endInclusive;
        }

        public List<Integer> getValues() {
            return values;
        }
    }

    List<Partition> partitions(final List<Integer> values,
                               final int partitionsCount) {
        final long[] prefixSums = initPrefixSums(values);
        final long[][] optimalPartitionsMaxValues = initOptimalPartitionsMaxValues(values, partitionsCount, prefixSums);
        final int[][] optimalPartitionsSeparators = initOptimalPartitionsSeparator(values, partitionsCount);
        for (int i = 1; i < values.size(); i++) {
            for (int j = 1; j < partitionsCount; j++) {
                optimalPartitionsMaxValues[i][j] = Long.MAX_VALUE;
                for (int x = 0; x < i; x++) {
                    final long cost = Math.max(optimalPartitionsMaxValues[x][j - 1], prefixSums[i] - prefixSums[x]);
                    if (optimalPartitionsMaxValues[i][j] > cost) {
                        optimalPartitionsMaxValues[i][j] = cost;
                        optimalPartitionsSeparators[i][j] = x;
                    }
                }
            }
        }
        return collectResult(new ArrayList<>(), values, optimalPartitionsSeparators, values.size(), partitionsCount);
    }

    private List<Partition> collectResult(final ArrayList<Partition> result,
                                          final List<Integer> values,
                                          final int[][] optimalPartitionsSeparators,
                                          final int valuesCount,
                                          final int partitionsCount) {
        if (partitionsCount == 1) {
            result.add(createPartition(values, 0, valuesCount - 1));
        } else {
            final int startPartitionIndex = optimalPartitionsSeparators[valuesCount - 1][partitionsCount - 1] + 1;
            collectResult(result, values, optimalPartitionsSeparators,
                    startPartitionIndex, partitionsCount - 1);
            result.add(createPartition(values, startPartitionIndex, valuesCount - 1));
        }
        return result;
    }

    private Partition createPartition(final List<Integer> values, final int startInclusive, final int endInclusive) {
        return new Partition(startInclusive, endInclusive, IntStream.rangeClosed(startInclusive, endInclusive)
                .mapToObj(values::get)
                .collect(toList()));
    }

    private int[][] initOptimalPartitionsSeparator(final List<Integer> values,
                                                   final int partitionsCount) {
        return new int[values.size()][partitionsCount];
    }

    private long[][] initOptimalPartitionsMaxValues(final List<Integer> values,
                                                    final int partitionsCount,
                                                    final long[] prefixSums) {
        final long[][] result = new long[values.size()][partitionsCount];
        for (int i = 0; i < prefixSums.length; i++) {
            result[i][0] = prefixSums[i];
        }
        final Long firstValue = Long.valueOf(values.get(0));
        for (int j = 0; j < partitionsCount; j++) {
            result[0][j] = firstValue;
        }
        return result;
    }

    private long[] initPrefixSums(final List<Integer> values) {
        final long[] result = new long[values.size()];
        long prevAddValue = 0;
        for (int i = 0; i < values.size(); i++) {
            final long addValue = values.get(i) + prevAddValue;
            result[i] = addValue;
            prevAddValue = addValue;
        }
        return result;
    }
}