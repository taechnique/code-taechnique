package io.taech;


import io.taech.graph.CompleteSearch;
import io.taech.graph.CompleteSearchManager;
import io.taech.graph.Node;
import io.taech.graph.SearchOption;
import io.taech.print.DefaultPrinter;
import io.taech.print.Member;
import io.taech.print.Printer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        CompleteSearch<Node> dfs = CompleteSearchManager.searchAs(SearchOption.DFS, 20);
//        CompleteSearch<Node> bfs = CompleteSearchManager.searchAs(SearchOption.BFS, 13);
//
//        dfs.addNeighbours(1, dfs.once(2));
//        dfs.addNeighbours(2, dfs.once(1, 5, 7));
//        dfs.addNeighbours(3, dfs.once(7));
//        dfs.addNeighbours(4, dfs.once(5, 9));
//        dfs.addNeighbours(5, dfs.once(2, 4));
//        dfs.addNeighbours(7, dfs.once(2, 3, 8, 13));
//        dfs.addNeighbours(8, dfs.once(7));
//        dfs.addNeighbours(9, dfs.once(4, 20));
//        dfs.addNeighbours(13, dfs.once(7, 20));
//        dfs.addNeighbours(20, dfs.once(9, 13));

//        bfs.addNeighbours(1, bfs.once(2, 4));
//        bfs.addNeighbours(2, bfs.once(1, 5));
//        bfs.addNeighbours(3, bfs.once(6));
//        bfs.addNeighbours(4, bfs.once(1));
//        bfs.addNeighbours(5, bfs.once(2, 6, 8));
//        bfs.addNeighbours(6, bfs.once(3, 5));
//        bfs.addNeighbours(7, bfs.once(8));
//        bfs.addNeighbours(8, bfs.once(5, 7, 9));
//        bfs.addNeighbours(9, bfs.once(8));
//        dfs.setTarget(20);

        dfs.search(dfs.get(1));

    }
}
