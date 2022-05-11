package io.taech.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BreathFirstSearch extends AbstractCompleteSearch {

    public BreathFirstSearch(int graphSize) {
        super.graph = IntStream.range(0, graphSize).mapToObj(i ->
                new Node(i)).collect(Collectors.toList()).toArray(new Node[graphSize]);
    }

    @Override
    public List<Node> once(Integer... nodes) {
        Objects.requireNonNull(nodes);
        List<Node> nodeList = new ArrayList<>();

        for (Integer node : nodes) {
            nodeList.add(super.graph[--node]);
        }

        return nodeList;
    }

    @Override
    public void search(Node node) {
        Queue<Node> queue = new LinkedList<>();

        queue.offer(node);

        while( ! queue.isEmpty()) {
            Node next = queue.poll();

            if(next.isNotVisited()) {
                next.visit();
                System.out.printf("\t queue: %s\n", queue);
                List<Node> neighbours = next.getNeighbours();

                for (int i = 0; i < neighbours.size(); i++) {
                    Node child = neighbours.get(i);

                    if (child.isNotVisited()) {
                        queue.offer(child);
                    }
                }
            }
        }


    }

    @Override
    public void searchWithoutRecursive(Node v) {

    }
}
