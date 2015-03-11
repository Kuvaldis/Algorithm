package kuvaldis.algorithm.backtracking;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.*;

public class AllSubsetsBacktrackTest {

    @Test
    public void testBacktrack() throws Exception {
        final AllSubsetsBacktrack<Integer> backtrack = new AllSubsetsBacktrack<>();
        final Set<Integer> inputSet = set(4, 92, 2121);
        final Set<Set<Integer>> outputSets = backtrack.backtrack(inputSet);
        assertEquals(8, outputSets.size());
        assertTrue(outputSets.contains(set()));
        assertTrue(outputSets.contains(set(4)));
        assertTrue(outputSets.contains(set(92)));
        assertTrue(outputSets.contains(set(2121)));
        assertTrue(outputSets.contains(set(4, 92)));
        assertTrue(outputSets.contains(set(4, 2121)));
        assertTrue(outputSets.contains(set(92, 2121)));
        assertTrue(outputSets.contains(set(4, 92, 2121)));
    }
    private static final Set<Integer> set(final Integer... elements) {
        return asList(elements).stream().collect(toSet());
    }
}