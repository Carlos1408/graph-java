package test;

import data.structure.*;

public class testDFS {
    public static void main(String[] args) {
        Graph graph = new Graph(false);
        graph.readFileInput("GrafoDFS.txt");
        graph.DFS();
        graph.printEdges();
    }
}
