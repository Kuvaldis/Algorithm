package kuvaldis.algorithm.dynamic.string;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

public class LongestMutualSequenceSearchTest {

    @Test
    public void testCompare() throws Exception {
        final LongestMutualSequenceSearch longestMutualSequenceSearch = new LongestMutualSequenceSearch();
        final Result result = longestMutualSequenceSearch.compare("democrat", "republican");
        assertEquals("eca", StreamSupport.stream(result.spliterator(), false)
                .filter(data -> Action.NONE.equals(data.getAction()))
                .map(ActionData::getLetterFrom)
                .map(String::valueOf)
                .collect(Collectors.joining()));
    }
}