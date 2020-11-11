package test;
import data.structure.*;

public class TestVertexCutNode {
    public static void main(String[] args) {
        Graph graph = new Graph(false);
        graph.readFileInput("Grafo_con_articulacion.txt");
        graph.DFS();
        graph.printVertexType();
    }
}
