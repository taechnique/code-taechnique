package io.taech.graph;


public class CompleteSearchManager {

    private static CompleteSearchManager manager = new CompleteSearchManager();

    private CompleteSearchManager() {
    }

    public static CompleteSearchManager getInstance() {
        return manager;
    }

    public static CompleteSearch<Node> searchAs(SearchOption constant, Integer graphSize) {
        switch (constant) {
            case BFS:
                return new BreathFirstSearch(graphSize);
            case DFS:
                return new DepthFirstSearch(graphSize);
            default:
                throw new NullPointerException();
        }
    }

}
