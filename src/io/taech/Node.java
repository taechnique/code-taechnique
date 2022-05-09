package io.taech;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        System.out.print(this);
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
}
