package io.taech.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node implements Comparable {

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

    public void visit() {
        this.visited = true;
        System.out.print(this);
    }

    public void cancelVisit() {
        this.visited = false;
    }

    public boolean hasNotVisitedNodes() {
        return (this.neighbours.stream().filter(n ->
                n.isNotVisited()).collect(Collectors.toList()).size() != 0);
    }

    public void addNeighbours(Node neighboursNode) {
        this.neighbours.add(neighboursNode);
    }

    public List<Node> getNeighbours() {
        return this.neighbours;
    }

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours.stream().sorted().collect(Collectors.toList());
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

    @Override
    public int compareTo(Object o) {
        if( ! (o instanceof Node))
            return 0;

        Node other = (Node) o;
        return other.info - this.info;
    }

    public Node getNextNoVisited() {
        return this.neighbours.stream().filter(n ->
                n.isNotVisited()).sorted().findFirst().get();
    }
}
