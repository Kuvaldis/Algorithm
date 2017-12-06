package kuvaldis.algorithm.backtracking;

import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;

import java.util.*;

import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.StreamSupport.stream;

public class GraphPathsBacktrack extends AbstractBacktrack<Vertex, Set<List<Vertex>>, GraphPathsBacktrack.Input> {

    public static class Input {
        private final Graph graph;
        private final Integer from;
        private final Integer to;

        public Input(final Graph graph, final Integer from, final Integer to) {
            this.graph = graph;
            this.from = from;
            this.to = to;
        }

        public Graph getGraph() {
            return graph;
        }

        public Integer getFrom() {
            return from;
        }

        public Integer getTo() {
            return to;
        }
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
