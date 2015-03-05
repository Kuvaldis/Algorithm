package kuvaldis.algorithm.graph.weighted;

import kuvaldis.algorithm.graph.weighted.domain.WeightedGraph;
import kuvaldis.algorithm.graph.weighted.domain.WeightedVertex;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.*;

public class AllMinimalPathsFloydWarshall {

    private final WeightedGraph graph;

    @Data
    public static class Result {
        @Getter(AccessLevel.PRIVATE)
        private final WeightedGraph graph;
        @Getter(AccessLevel.PRIVATE)
        private final Map<WeightedVertex, Map<WeightedVertex, Integer>> minimalPathDistances;
        @Getter(AccessLevel.PRIVATE)
        private final Map<WeightedVertex, Map<WeightedVertex, WeightedVertex>> parents;

        public List<WeightedVertex> minimalPath(final Integer from, final Integer to) {
            final WeightedVertex fromVertex = graph.getVertex(from);
            final WeightedVertex toVertex = graph.getVertex(to);
            return doMinimalPath(fromVertex, toVertex, true);
        }

        private List<WeightedVertex> doMinimalPath(final WeightedVertex fromVertex,
                                                   final WeightedVertex toVertex,
                                                   final boolean includeFromTo) {
            final WeightedVertex parentVertex = parents.get(fromVertex).get(toVertex);
            final LinkedList<WeightedVertex> result = new LinkedList<>();
            if (includeFromTo) {
                result.add(fromVertex);
            }
            if (parentVertex != null) {
                result.addAll(doMinimalPath(fromVertex, parentVertex, false));
                result.add(parentVertex);
                result.addAll(doMinimalPath(parentVertex, toVertex, false));
            }
            if (includeFromTo) {
                result.add(toVertex);
            }
            return result;
        }

        public Integer minimalDistance(final Integer from, final Integer to) {
            return minimalPathDistances.get(graph.getVertex(from)).get(graph.getVertex(to));
        }
    }

    public AllMinimalPathsFloydWarshall(WeightedGraph graph) {
        this.graph = graph;
    }

    public Result build() {
        final Map<WeightedVertex, Map<WeightedVertex, Integer>> adjacencyMatrix = new HashMap<>();
        final Map<WeightedVertex, Map<WeightedVertex, WeightedVertex>> parents = new HashMap<>();
        for (int k = 1; k <= graph.size(); k++) {
            final WeightedVertex kVertex = graph.getVertex(k);
            for (int i = 1; i <= graph.size(); i++) {
                final WeightedVertex iVertex = graph.getVertex(i);
                for (int j = 1; j <= graph.size(); j++) {
                    final WeightedVertex jVertex = graph.getVertex(j);
                    final Integer ikDistance = getDistance(iVertex, kVertex, adjacencyMatrix);
                    final Integer kjDistance = getDistance(kVertex, jVertex, adjacencyMatrix);
                    final Integer kDistance = ikDistance != null && kjDistance != null ? ikDistance + kjDistance : null;
                    final Integer currentDistance = getDistance(iVertex, jVertex, adjacencyMatrix);
                    if (currentDistance == null || kDistance != null && kDistance < currentDistance) {
                        adjacencyMatrix.get(iVertex).put(jVertex, kDistance);
                        addParent(iVertex, jVertex, kVertex, parents);
                    }
                }
            }
        }
        return new Result(graph, adjacencyMatrix, parents);
    }

    private void addParent(final WeightedVertex fromVertex,
                           final WeightedVertex toVertex,
                           final WeightedVertex parentVertex,
                           final Map<WeightedVertex, Map<WeightedVertex, WeightedVertex>> parents) {
        Map<WeightedVertex, WeightedVertex> fromParents = parents.get(fromVertex);
        if (fromParents == null) {
            fromParents = new HashMap<>();
            parents.put(fromVertex, fromParents);
        }
        fromParents.put(toVertex, parentVertex);
    }

    private Integer getDistance(final WeightedVertex from,
                                final WeightedVertex to,
                                final Map<WeightedVertex, Map<WeightedVertex, Integer>> adjacencyMatrix) {
        Map<WeightedVertex, Integer> adjacentVertices = adjacencyMatrix.get(from);
        if (adjacentVertices == null) {
            adjacentVertices = new HashMap<>();
            adjacencyMatrix.put(from, adjacentVertices);
        }
        Integer result = adjacentVertices.get(to);
        if (result == null) {
            result = from.getWeight(to);
            adjacentVertices.put(to, result);
        }
        return result;
    }
}
