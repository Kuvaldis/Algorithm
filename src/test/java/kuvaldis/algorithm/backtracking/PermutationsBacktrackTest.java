package kuvaldis.algorithm.backtracking;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.*;

public class PermutationsBacktrackTest {

    @Test
    public void testBacktrack() throws Exception {
        final PermutationsBacktrack<Integer> backtrack = new PermutationsBacktrack<>();
        final Set<Integer> inputSet = set(4, 92, 2121);
        final Set<List<Integer>> outputSets = backtrack.generate(inputSet);
        assertEquals(6, outputSets.size());
        assertTrue(outputSets.contains(list(4, 92, 2121)));
        assertTrue(outputSets.contains(list(4, 2121, 92)));
        assertTrue(outputSets.contains(list(92, 4, 2121)));
        assertTrue(outputSets.contains(list(92, 2121, 4)));
        assertTrue(outputSets.contains(list(2121, 4, 92)));
        assertTrue(outputSets.contains(list(2121, 92, 4)));
    }

    private static Set<Integer> set(final Integer... elements) {
        return list(elements).stream().collect(toSet());
    }

    private static List<Integer> list(final Integer... elements) {
        return asList(elements);
    }
}