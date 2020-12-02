package test;
import data.structure.*;

public class TestPath {
    public static void main(String[] args) {
        Graph graph = new Graph(false);
        graph.readFileInput("Grafo4.txt");
        graph.shortPath(graph.getVertexs()[0], graph.getVertexs()[5]);
    }
}
