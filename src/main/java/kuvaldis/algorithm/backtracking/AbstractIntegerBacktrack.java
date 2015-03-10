package kuvaldis.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Based on DFS, as it takes less memory. We don't care about execution stack depth, consider it infinite.
 *
 */
public abstract class AbstractIntegerBacktrack<R, D> implements Backtrack<R, D> {

    @Override
    public R backtrack(D input) {
        final List<Integer> solutionsList = new ArrayList<>();
        doBacktrack(solutionsList, 0, input);
        return constructResult(input);
    }

    protected boolean doBacktrack(final List<Integer> solutionsList, final int start, final D input) {
        int k = start;
        if (isSolution(solutionsList, k, input)) {
            processSolution(solutionsList, k, input);
        } else {
            final List<Integer> candidates = constructCandidates(solutionsList, ++k, input);
            for (Integer candidate : candidates) {
                addToSolutionsList(solutionsList, k, candidate);
                if (makeMove(solutionsList, k, input)) {
                    return true;
                }
                if (doBacktrack(solutionsList, k, input)) {
                    return true;
                }
                if (unmakeMove(solutionsList, k, input)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void addToSolutionsList(final List<Integer> solutionsList, final int addPosition, final Integer candidate) {
        if (solutionsList.size() - 1 < addPosition) {
            solutionsList.add(candidate);
        } else {
            solutionsList.set(addPosition, candidate);
        }
    }

    protected boolean makeMove(List<Integer> solutionsList, int k, D input) {
        return false;
    }

    protected boolean unmakeMove(List<Integer> solutionsList, int k, D input) {
        return false;
    }

    protected abstract R constructResult(D input);

    protected abstract boolean isSolution(List<Integer> solutionsList, int k, D input);

    protected abstract void processSolution(List<Integer> solutionsList, int k, D input);

    protected abstract List<Integer> constructCandidates(List<Integer> solutionsList, int k, D input);
}
