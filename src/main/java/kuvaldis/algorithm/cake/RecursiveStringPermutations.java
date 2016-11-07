package kuvaldis.algorithm.cake;

import java.util.*;
import java.util.stream.Collectors;

public class RecursiveStringPermutations {

    private Set<String> solutions = new HashSet<>();
    private List<Character> path = new ArrayList<>();

    public Set<String> permutations(final String s) {
        final char[] chars = s.toCharArray();
        final List<Character> charsList = new ArrayList<>(chars.length);
        for (final char c : chars) {
            charsList.add(c);
        }
        permutations(charsList);
        solutions.remove(s);
        return solutions;
    }

    private void permutations(final List<Character> chars) {
        for (Character c : chars) {
            path.add(c);
            final List<Character> candidates = candidates(c, chars);
            if (!candidates.isEmpty()) {
                permutations(candidates);
            } else {
                addSolution();
            }
            path.remove(path.size() - 1);
        }
    }

    private void addSolution() {
        final char[] chars = new char[path.size()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = path.get(i);
        }
        solutions.add(new String(chars));
    }

    private List<Character> candidates(final Character exclude, final List<Character> chars) {
        final List<Character> result = new ArrayList<>(chars.size());
        result.addAll(chars.stream().collect(Collectors.toList()));
        result.remove(exclude);
        return result;
    }
}
