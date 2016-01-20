package kuvaldis.algorithm.cake;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class RecursiveStringPermutationsTest {

    @Test
    public void testPermutations() throws Exception {
        Set<String> permutations = new RecursiveStringPermutations().permutations("abc");
        assertTrue(permutations.contains("acb"));
        assertTrue(permutations.contains("bac"));
        assertTrue(permutations.contains("bca"));
        assertTrue(permutations.contains("cab"));
        assertTrue(permutations.contains("cba"));
        assertEquals(5, permutations.size());

        permutations = new RecursiveStringPermutations().permutations("aabc");
        assertTrue(permutations.contains("aacb"));
        assertTrue(permutations.contains("abac"));
        assertTrue(permutations.contains("abca"));
        assertTrue(permutations.contains("acab"));
        assertTrue(permutations.contains("acba"));
        assertTrue(permutations.contains("baac"));
        assertTrue(permutations.contains("baca"));
        assertTrue(permutations.contains("bcaa"));
        assertTrue(permutations.contains("caab"));
        assertTrue(permutations.contains("caba"));
        assertTrue(permutations.contains("cbaa"));
        assertEquals(11, permutations.size());
    }
}