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

//        searchWithThrowUp(v);
//        if(v != null)
//            return;

        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(v);

        while (!nodeStack.isEmpty()) {

            Node next = nodeStack.pop();
            if (next.isNotVisited()) {
                next.visit();
                List<Node> neighbours = next.getNeighbours();

                for (int i = 0; i < neighbours.size(); i++) {
                    Node child = neighbours.get(i);

                    if (child.isNotVisited())
                        nodeStack.push(child);

                }
                System.out.printf("\t stack -> %-30s\n", nodeStack);
            }
        }
    }

    public void searchWithThrowUp(Node v) {
        final Stack<Node> nodeStack = new Stack<>();

        nodeStack.push(v);
        while( ! nodeStack.isEmpty()) {

            final Node next = nodeStack.peek();
            if(next.isNotVisited()) {

                next.visit();

                if(next.hasNotVisitedNodes()) {
                    final Node nextNoVisited = next.getNextNoVisited();
                    nodeStack.push(nextNoVisited);
                } else {
                    nodeStack.pop();
                    if( ! nodeStack.isEmpty()){
                        final Node parent = nodeStack.peek();
                        parent.cancelVisit();
                    }
                }
            }

            System.out.printf("\t stack -> %-30s\n", nodeStack);
        }
    }

    @Override
    public void searchWithRecursive(Node v) {

        System.out.println(v);
        v.visit();

        List<Node> neighbours = v.getNeighbours();

        for (int i = 0; i < neighbours.size(); i++) {
            Node nei = neighbours.get(i);

            if (nei != null && nei.isNotVisited()) {
                search(nei);
            }
        }
    }

}
