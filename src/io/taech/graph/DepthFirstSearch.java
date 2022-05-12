package io.taech.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class DepthFirstSearch extends AbstractCompleteSearch {


    public DepthFirstSearch(int graphSize) {
        super.graph = IntStream.range(0, graphSize).mapToObj(i ->
                new Node(i)).collect(Collectors.toList()).toArray(new Node[graphSize]);
    }

    @Override
    public List<Node> once(Integer... nodes) {
        Objects.requireNonNull(nodes);
        List<Node> nodeList = new ArrayList<>();

        for(Integer node : nodes) {
            nodeList.add(super.graph[--node]);
        }

        return nodeList;
    }

    @Override
    public void search(Node v) {
        Stack<Node> nodeStack = new Stack<>();
        Stack<Node> ways = new Stack<Node>();

        nodeStack.push(v);
        ways.push(v);

        while (!nodeStack.isEmpty()) {

            Node next = nodeStack.pop();
            if (next.isNotVisited()) {
                next.visit();
                System.out.printf("\t stack: %s\t ways -> %s\n ", nodeStack, ways);
                List<Node> neighbours = next.getNeighbours();

                for (int i = 0; i < neighbours.size(); i++) {
                    Node child = neighbours.get(i);
                    if (child.isNotVisited()){
                        nodeStack.push(child);
                    }
                }
            }
        }
    }

    @Override
    public void searchWithRecursive(Node v) {

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


}
