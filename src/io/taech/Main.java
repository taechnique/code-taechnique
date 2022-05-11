package io.taech;


import io.taech.graph.CompleteSearch;
import io.taech.graph.CompleteSearchManager;
import io.taech.graph.Node;
import io.taech.graph.SearchConstant;

public class Main {

    public static void main(String[] args) throws Exception {
        CompleteSearch<Node> bfs = CompleteSearchManager.searchAs(SearchConstant.BFS, 13);

//        dfs.addNeighbours(0, dfs.once(1));
//        dfs.addNeighbours(1, dfs.once(0, 4, 6));
//        dfs.addNeighbours(2, dfs.once(6));
//        dfs.addNeighbours(3, dfs.once(4));
//        dfs.addNeighbours(4, dfs.once(1, 3, 8));
//        dfs.addNeighbours(6, dfs.once(1, 2, 7, 12));
//        dfs.addNeighbours(7, dfs.once(6));
//        dfs.addNeighbours(8, dfs.once(3));
//        dfs.addNeighbours(12, dfs.once(6));

        bfs.addNeighbours(1, bfs.once(2, 4));
        bfs.addNeighbours(2, bfs.once(1, 5));
        bfs.addNeighbours(3, bfs.once(6));
        bfs.addNeighbours(4, bfs.once(1));
        bfs.addNeighbours(5, bfs.once(2, 6, 8));
        bfs.addNeighbours(6, bfs.once(3, 5));
        bfs.addNeighbours(7, bfs.once(8));
        bfs.addNeighbours(8, bfs.once(5, 7, 9));
        bfs.addNeighbours(9, bfs.once(8));

        bfs.search(bfs.get(1));
    }
}
