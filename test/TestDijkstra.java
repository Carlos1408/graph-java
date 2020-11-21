package test;

import data.structure.*;

public class TestDijkstra {
    public static void main(String[] args) {
        Graph graph = new Graph(false);
        graph.readFileInput("Grafo3.txt");
        graph.dijkstra();
        graph.printDijkstra();
    }
}
