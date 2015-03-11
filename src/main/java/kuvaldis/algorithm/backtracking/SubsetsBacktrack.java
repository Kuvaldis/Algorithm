package kuvaldis.algorithm.backtracking;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class SubsetsBacktrack<E> extends AbstractBacktrack<Boolean, Set<Set<E>>, Set<E>> {

    private final Set<Set<E>> result = new HashSet<>();
    private List<E> orderedSetList = emptyList();

    @Override
    protected void prepare(Set<E> input) {
        orderedSetList = input.stream().collect(toList());
    }

    @Override
    protected Set<Set<E>> constructResult(Set<E> input) {
        return result;
    }

    @Override
    protected boolean isSolution(List<Boolean> solutionsList, int solutionsSize, Set<E> input) {
        return solutionsSize == input.size();
    }

    @Override
    protected void processSolution(List<Boolean> solutionsList, int solutionsSize, Set<E> input) {
        result.add(IntStream.range(0, solutionsSize)
                .mapToObj(i -> solutionsList.get(i) ? orderedSetList.get(i) : null)
                .filter(Objects::nonNull)
                .collect(toSet()));
    }

    @Override
    protected List<Boolean> constructCandidates(List<Boolean> solutionsList, int solutionsSize, Set<E> input) {
        return Arrays.asList(Boolean.TRUE, Boolean.FALSE);
    }
}