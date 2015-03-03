package kuvaldis.algorithm.graph.weighted;

import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;
import kuvaldis.algorithm.graph.weighted.domain.WeightedEdge;
import kuvaldis.algorithm.graph.weighted.domain.WeightedGraph;
import kuvaldis.algorithm.graph.weighted.domain.WeightedVertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;

public class MinimalSpanningTreeKruskal {

    private final WeightedGraph graph;

    private final Map<WeightedVertex, UnionData> unionDataMap = new HashMap<>();

    public MinimalSpanningTreeKruskal(WeightedGraph graph) {
        this.graph = graph;
    }

    public Set<WeightedEdge> build() {
        final Set<WeightedEdge> result = new HashSet<>();
        for (WeightedEdge edge : sortedEdges()) {
            if (!sameComponent(edge.getTailVertex(), edge.getHeadVertex())) {
                unionComponents(edge.getTailVertex(), edge.getHeadVertex());
                result.add(edge);
            }
        }
        return result;
    }

    private void unionComponents(final WeightedVertex v1, final WeightedVertex v2) {
        final WeightedVertex root1 = root(v1);
        final WeightedVertex root2 = root(v2);
        if (Objects.equals(root1, root2)) {
            throw new IllegalStateException(
                    String.format("Cannot union components with the same roots: %s, %s", v1, v2));
        }
        final UnionData rootUnionData1 = unionDataMap.get(root1);
        final UnionData rootUnionData2 = unionDataMap.get(root2);
        int newSize = rootUnionData1.getSize() + rootUnionData2.getSize();
        if (rootUnionData1.getSize() >= rootUnionData2.getSize()) {
            rootUnionData1.setSize(newSize);
            rootUnionData2.setRoot(rootUnionData1.getRoot());
        } else {
            rootUnionData2.setSize(newSize);
            rootUnionData1.setRoot(rootUnionData2.getRoot());
        }
    }

    private boolean sameComponent(final WeightedVertex v1, final WeightedVertex v2) {
        return Objects.equals(root(v1), root(v2));
    }

    private WeightedVertex root(WeightedVertex vertex) {
        WeightedVertex result;
        UnionData unionData = unionDataMap.get(vertex);
        if (unionData == null) {
            unionData = new UnionData(vertex, 1);
            unionDataMap.put(vertex, unionData);
            result = vertex;
        } else {
            result = vertex;
            while (!Objects.equals(unionData.getRoot(), result)) {
                result = unionData.getRoot();
                unionData = unionDataMap.get(result);
            }
        }
        return result;
    }

    private List<WeightedEdge> sortedEdges() {
        return IntStream.range(1, graph.size() + 1)
                .mapToObj(graph::getVertex)
                .flatMap(vertex -> stream(spliteratorUnknownSize(vertex.edgesIterator(), Spliterator.SIZED), false))
                .sorted()
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    private static final class UnionData {
        private WeightedVertex root;
        private int size;
    }
}
