package io.taech;


public class Main {

    public static void main(String[] args) throws Exception {
        DepthFirstSearch dfs = new DepthFirstSearch(13);
        dfs.addNeighbours(0, dfs.once(1));
        dfs.addNeighbours(1, dfs.once(0, 4, 6));
        dfs.addNeighbours(2, dfs.once(6));
        dfs.addNeighbours(3, dfs.once(4));
        dfs.addNeighbours(4, dfs.once(1, 3, 8));
        dfs.addNeighbours(6, dfs.once(1, 2, 7, 12));
        dfs.addNeighbours(7, dfs.once(6));
        dfs.addNeighbours(8, dfs.once(3));
        dfs.addNeighbours(12, dfs.once(6));

        dfs.searchWithStack(dfs.get(0));
    }
}
