package kuvaldis.algorithm.graph.nonweighted.application;

import kuvaldis.algorithm.graph.nonweighted.bfs.AbstractBreadthFirstSearch;
import kuvaldis.algorithm.graph.nonweighted.domain.Graph;
import kuvaldis.algorithm.graph.nonweighted.domain.Vertex;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BipartiteCheck extends AbstractBreadthFirstSearch<Boolean> {

    public BipartiteCheck(Graph graph) {
        super(graph);
    }

    private enum Color {
        BLACK {
            @Override
            public Color getInverted() {
                return Color.WHITE;
            }
        }, WHITE {
            @Override
            public Color getInverted() {
                return Color.BLACK;
            }
        };
        public abstract Color getInverted();
    }

    private Boolean result = Boolean.TRUE;
    private final Map<Vertex, Color> colors = new HashMap<>();

    @Override
    protected boolean startSubSearch(Vertex rootVertex) {
        colors.put(rootVertex, Color.WHITE);
        return true;
    }

    @Override
    protected boolean processEdge(Vertex v, Vertex y) {
        if (Objects.equals(colors.get(v), colors.get(y))) {
            return result = Boolean.FALSE;
        }
        colors.put(y, colors.get(v).getInverted());
        return true;
    }

    @Override
    public Boolean result() {
        return result;
    }
}
