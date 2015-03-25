package kuvaldis.algorithm.backtracking;

import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.*;

import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.StreamSupport.stream;

public class GraphPathsBacktrack extends AbstractBacktrack<Vertex, Set<List<Vertex>>, GraphPathsBacktrack.Input> {

    @Data
    public static class Input {
        @Getter(AccessLevel.PRIVATE)
        private final Graph graph;
        @Getter(AccessLevel.PRIVATE)
        private final Integer from;
        @Getter(AccessLevel.PRIVATE)
        private final Integer to;
    }

    private final Set<List<Vertex>> result = new HashSet<>();

    @Override
    protected Set<List<Vertex>> constructResult(Input input) {
        return result;
    }

    @Override
    protected boolean isSolution(List<Vertex> solutionsList, int solutionsSize, Input input) {
        return solutionsSize > 0 && solutionsList.get(solutionsSize - 1).getNumber() == input.to;
    }

    @Override
    protected boolean processSolution(List<Vertex> solutionsList, int solutionsSize, Input input) {
        result.add(solutionsList.stream()
                .limit(solutionsSize)
                .collect(toList()));
        return false;
    }

    @Override
    protected List<Vertex> constructCandidates(List<Vertex> solutionsList, int solutionsSize, Input input) {
        if (solutionsSize == 0) {
            return Collections.singletonList(input.getGraph().getVertex(input.getFrom()));
        } else {
            final Set<Vertex> inSolution = solutionsList.stream().collect(toSet());
            final Vertex lastVertex = solutionsList.get(solutionsSize - 1);
            return stream(spliteratorUnknownSize(lastVertex.edgesIterator(), Spliterator.SIZED), false)
                    .filter(candidate -> !inSolution.contains(candidate))
                    .collect(toList());
        }
    }
}
