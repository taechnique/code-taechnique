package io.taech.graph;

import java.util.List;
import java.util.Objects;

abstract class AbstractCompleteSearch implements CompleteSearch<Node> {

    protected Node[] graph;

    protected final void checkRange(int index) {

        if (index <= 0 && this.graph.length <= index) {
            throw new ArrayIndexOutOfBoundsException("범위 초과");
        }
    }

    @Override
    public void addNeighbours(Integer index, List<Node> nodes) {
        checkRange(--index);
        Objects.requireNonNull(nodes);

        this.graph[index].setNeighbours(nodes);
    }

    @Override
    public final Node get(Integer index) {
        checkRange(--index);

        return this.graph[index];
    }
}
