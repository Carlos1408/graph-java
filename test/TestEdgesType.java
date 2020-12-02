package test;
import data.structure.*;

public class TestEdgesType {
    public static void main(String[] args) {
        Graph graph = new Graph(false);
        graph.readFileInput("Grafo4.txt");
        graph.DFS();
        graph.printEdges();
    }
}
