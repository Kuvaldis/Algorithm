package kuvaldis.algorithm.dynamic.string;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Cost implements Comparable<Cost> {

    public static final Cost ZERO = new Cost(false, 0);

    public static final Cost ONE = new Cost(false, 1);

    public static final Cost INFINITY = new Cost(true, null);

    public static Cost of(final Integer value) {
        return new Cost(false, value);
    }

    private final boolean infinite;
    private final Integer value;

    private Cost(boolean infinite, Integer value) {
        this.infinite = infinite;
        this.value = value;
    }

    public Cost add(final Cost cost) {
        if (infinite || cost.infinite) return INFINITY;
        return of(value + cost.value);
    }

    @Override
    public int compareTo(Cost o) {
        if (infinite && o.infinite) return 0;
        if (infinite) return 1;
        if (o.infinite) return -1;
        return value.compareTo(o.value);
    }
}
