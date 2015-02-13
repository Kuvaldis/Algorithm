package kuvaldis.graph.application;

import kuvaldis.graph.Search;
import kuvaldis.graph.bfs.AbstractBreadthFirstSearch;
import kuvaldis.graph.domain.Graph;
import kuvaldis.graph.domain.Vertex;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BipartiteCheck implements Search<Boolean> {

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

    private final Graph graph;
    private Boolean result;
    private final Map<Vertex, Color> colors = new HashMap<>();

    public BipartiteCheck(Graph graph) {
        this.graph = graph;
    }

    @Override
    public Search<Boolean> search() {
        for (int i = 1; i <= graph.size(); i++) {
            final Vertex vertex = graph.getVertex(i);
            if (!colors.containsKey(vertex)) {
                colors.put(vertex, Color.WHITE);
                result = new BipartiteCheckBfs(graph, i).search().result();
                if (!result) break;
            }
        }
        return this;
    }

    @Override
    public Boolean result() {
        return result;
    }

    private final class BipartiteCheckBfs extends AbstractBreadthFirstSearch<Boolean> {

        private Boolean result = Boolean.TRUE;

        public BipartiteCheckBfs(Graph graph, Integer rootNumber) {
            super(graph, rootNumber);
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
}
