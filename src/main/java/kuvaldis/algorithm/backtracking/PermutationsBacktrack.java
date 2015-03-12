package kuvaldis.algorithm.backtracking;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class PermutationsBacktrack<E> extends AbstractBacktrack<Integer, Set<List<E>>, Set<E>> {

    private final Set<List<E>> result = new HashSet<>();
    private List<E> orderedSetList = emptyList();

    @Override
    protected void prepare(Set<E> input) {
        orderedSetList = input.stream().collect(toList());
    }

    @Override
    protected Set<List<E>> constructResult(Set<E> input) {
        return result;
    }

    @Override
    protected boolean isSolution(List<Integer> solutionsList, int solutionsSize, Set<E> input) {
        return solutionsSize == input.size();
    }

    @Override
    protected void processSolution(List<Integer> solutionsList, int solutionsSize, Set<E> input) {
        result.add(solutionsList.stream()
                .map(orderedSetList::get)
                .collect(toList()));
    }

    @Override
    protected List<Integer> constructCandidates(List<Integer> solutionsList, int solutionsSize, Set<E> input) {
        return IntStream.range(0, orderedSetList.size())
                .filter(index -> {
                    final int foundIndex = solutionsList.indexOf(index);
                    return 0 > foundIndex || foundIndex >= solutionsSize;
                })
                .mapToObj(Integer::new)
                .collect(Collectors.toList());
    }
}
