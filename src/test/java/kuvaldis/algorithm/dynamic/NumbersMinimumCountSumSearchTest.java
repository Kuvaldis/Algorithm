package kuvaldis.algorithm.dynamic;

import junit.framework.TestCase;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.*;

public class NumbersMinimumCountSumSearchTest extends TestCase {

    public void testSearch() throws Exception {
        final NumbersMinimumCountSumSearch search = new NumbersMinimumCountSumSearch();
        assertEquals(map(asList(3, 5), asList(4, 0)), search.search(asList(3, 5).stream().collect(toSet()), 12l));
        assertEquals(map(asList(1, 5), asList(1, 2)), search.search(asList(1, 5).stream().collect(toSet()), 11l));
        assertEquals(map(asList(2, 3, 5), asList(0, 2, 1)), search.search(asList(2, 3, 5).stream().collect(toSet()), 11l));
        assertEquals(map(asList(1, 2, 3, 6), asList(1, 0, 0, 1)), search.search(asList(1, 2, 3, 6).stream().collect(toSet()), 7l));
        assertEquals(map(asList(1, 2, 5), asList(0, 1, 1)), search.search(asList(1, 2, 5).stream().collect(toSet()), 7l));
        assertEquals(map(asList(1, 2, 5), asList(1, 1, 1)), search.search(asList(1, 2, 5).stream().collect(toSet()), 8l));
        assertEquals(map(asList(1, 10, 25, 50), asList(0, 3, 0, 1)), search.search(asList(1, 10, 25, 50).stream().collect(toSet()), 80l));
        assertEquals(Collections.emptyMap(), search.search(singletonList(3).stream().collect(toSet()), 4l));
    }

    private final Map<Integer, Integer> map(final List<Integer> keys, final List<Integer> values) {
        return IntStream.range(0, keys.size())
                .boxed()
                .collect(toMap(keys::get, values::get));
    }
}