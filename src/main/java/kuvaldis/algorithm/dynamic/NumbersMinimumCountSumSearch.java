package kuvaldis.algorithm.dynamic;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.*;
import static java.util.stream.Stream.iterate;

/**
 * Finds minimum set and values number from given set of values, needed to obtain given sum
 * For instance to if we have only set of {1, 5} and we want to collect 12, then the result will be
 * 2 times 5 and 2 times 1
 */
public class NumbersMinimumCountSumSearch {

    private static final Comparator<ItemData> TOTAL_ITEM_COUNT_COMPARATOR =
            (o1, o2) -> o1.totalCount.compareTo(o2.totalCount);

    public class ItemData implements Comparable<ItemData> {
        private final Integer cost;
        private final ItemData parent;
        private final Integer additionalCount;
        private final Integer totalCount;

        public ItemData(final Integer cost, final ItemData parent, final Integer additionalCount, final Integer totalCount) {
            this.cost = cost;
            this.parent = parent;
            this.additionalCount = additionalCount;
            this.totalCount = totalCount;
        }

        public Integer getCost() {
            return cost;
        }

        public ItemData getParent() {
            return parent;
        }

        public Integer getAdditionalCount() {
            return additionalCount;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        @Override
        public int compareTo(@SuppressWarnings("NullableProblems") final ItemData o) {
            return this.totalCount.compareTo(o.totalCount);
        }
    }

    public Map<Integer, Integer> search(final Set<Integer> items, final Long sum) {
        final List<Integer> costs = items.stream()
                .sorted(Integer::compareTo)
                .collect(toList());
        final ItemData[][] sumTable = initSumTable(costs, sum);
        for (int i = 1; i <= costs.size(); i++) {
            final Integer currentCost = costs.get(i - 1);
            for (int j = 1; j <= sum; j++) {
                final ItemData fromUpParent = fromParent(sumTable[i - 1][j], currentCost, 0);
                if (currentCost > j) {
                    sumTable[i][j] = fromUpParent;
                } else {
                    sumTable[i][j] = asList(fromParent(sumTable[i][j - currentCost], currentCost, 1),
                            fromParent(sumTable[i - 1][j - currentCost], currentCost, 1),
                            fromUpParent).stream()
                            .min(TOTAL_ITEM_COUNT_COMPARATOR)
                            .get();
                }
            }
        }
        return restoreResult(sumTable, costs);
    }

    private Map<Integer, Integer> restoreResult(final ItemData[][] sumTable, final List<Integer> costs) {
        return ofNullable(lastItemData(sumTable))
                .map(lastItemData -> merge(costs.stream()
                                .collect(groupingBy(Function.<Integer>identity(), summingInt(v -> 0))),
                        iterate(lastItemData, ItemData::getParent)
                                .limit(lastItemData.totalCount)
                                .collect(groupingBy(ItemData::getCost, summingInt(ItemData::getAdditionalCount)))))
                .orElse(emptyMap());
    }

    private Map<Integer, Integer> merge(final Map<Integer, Integer> to, Map<Integer, Integer> from) {
        to.putAll(from);
        return to;
    }

    private ItemData lastItemData(final ItemData[][] sumTable) {
        return IntStream.range(0, sumTable.length)
                .mapToObj(i -> sumTable[i][sumTable[i].length - 1])
                .filter(it -> it.totalCount != Integer.MAX_VALUE)
                .min(TOTAL_ITEM_COUNT_COMPARATOR)
                .orElse(null);
    }

    private ItemData fromParent(ItemData parent, final Integer cost, final int additionalCount) {
        if (parent.additionalCount == 0 && parent.getParent() != null &&
                !Objects.equals(parent.getCost(), (parent.getParent().getCost()))) {
            parent = parent.getParent();
        }
        return new ItemData(cost, parent, Integer.MAX_VALUE == parent.totalCount ? Integer.MAX_VALUE : additionalCount,
                Integer.MAX_VALUE == parent.totalCount ? parent.totalCount : parent.totalCount + additionalCount);
    }

    private ItemData[][] initSumTable(List<Integer> costs, Long sum) {
        final ItemData[][] result = new ItemData[costs.size() + 1][sum.intValue() + 1];
        result[0][0] = new ItemData(0, null, 0, 0);
        for (int j = 1; j <= sum; j++) {
            result[0][j] = new ItemData(0, null, Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        for (int i = 1; i <= costs.size(); i++) {
            result[i][0] = new ItemData(costs.get(i - 1), null, 0, 0);
        }
        return result;
    }
}