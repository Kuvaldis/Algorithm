package kuvaldis.algorithm.dynamic.string;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

public class SearchSimilarSubstringTest {

    @Test
    public void testCompare() throws Exception {
        final SearchSimilarSubstring searchSimilarSubstring = new SearchSimilarSubstring();
        final Result result = searchSimilarSubstring.compare("tea", "There is no great genius without some touch of madness");
        assertEquals(Cost.of(1), result.last().getCost());
        assertEquals("rea", StreamSupport.stream(result.spliterator(), false)
                .map(ActionData::getLetterFrom)
                .map(String::valueOf)
                .collect(Collectors.joining()));
    }
}