package io.taech.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

    int info;
    boolean visited;
    List<Node> neighbours;

    public Node(int info) {
        this.info = info;
        this.visited = false;
        this.neighbours = new ArrayList<>();
    }

    public boolean isNotVisited() {
        return ( ! this.visited);
    }

    public Node visit() {
        this.visited = true;
        System.out.print(this+"\n");
        return this;
    }

    public void addNeighbours(Node neighboursNode) {
        this.neighbours.add(neighboursNode);
    }

    public List<Node> getNeighbours() {
        return this.neighbours;
    }

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

    @Override
    public String toString() {
        return " ("+(this.info +1)+") ";
    }

    @Override
    public boolean equals(Object obj) {
        if( ! (obj instanceof Node))
            return false;

        final Node other = (Node) obj;
        return this.info == other.info;
    }
}
