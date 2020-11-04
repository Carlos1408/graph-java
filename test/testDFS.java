package test;

import data.structure.*;

public class testDFS {
    public static void main(String[] args) {
        Graph graph = new Graph(true);
        graph.readFileInput("GrafoDFS.txt");
        //graph.DFS(graph.getVertexList().getHead().getData());
        graph.DFS();
    }
}
