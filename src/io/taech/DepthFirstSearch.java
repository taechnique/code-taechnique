package io.taech;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DepthFirstSearch {

    private Node[] graph;

    public DepthFirstSearch(int graphSize) {
        this.graph = IntStream.range(0, graphSize).mapToObj(i ->
                new Node(i)).collect(Collectors.toList()).toArray(new Node[graphSize]);
    }

    public Node get(int index) {
        checkRange(index);

        return this.graph[index];
    }

    private void checkRange(int index) {

        if (index >= graph.length) {
            throw new ArrayIndexOutOfBoundsException("범위 초과");
        }
    }

    public List<Node> once(Integer... nodes) {
        Objects.requireNonNull(nodes);
        List<Node> nodeList = new ArrayList<>();

        for(Integer node : nodes) {
            nodeList.add(this.graph[node]);
        }

        return nodeList;
    }

    public void addNeighbours(int index, List<Node> nodes) {
        checkRange(index);
        Objects.requireNonNull(nodes);

        this.graph[index].setNeighbours(nodes);
    }

    public void search(Node v) {
        System.out.println(v);
        v.visited = true;

        List<Node> neighbours = v.getNeighbours();

        for (int i = 0; i < neighbours.size(); i++) {
            Node nei = neighbours.get(i);

            if (nei != null && nei.isNotVisited()) {
                search(nei);
            }
        }
    }

    public void searchWithStack(Node v) {
        Stack<Node> nodeStack = new Stack<>();

        nodeStack.push(v);

        while( ! nodeStack.isEmpty()) {
            Node next = nodeStack.pop();

            if(next.isNotVisited()) {
                next.visit();
                System.out.printf("\tgraph: %s\n", nodeStack);
                List<Node> neighbours = next.getNeighbours();

                for (int i = 0; i < neighbours.size(); i++) {
                    Node neighbour = neighbours.get(i);
                    if(neighbour.isNotVisited())
                        nodeStack.push(neighbour);
                }

            }
        }

    }


}
