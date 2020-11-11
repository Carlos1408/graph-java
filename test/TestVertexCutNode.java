package test;
import data.structure.*;

public class TestVertexCutNode {
    public static void main(String[] args) {
        Graph graph = new Graph(false);
        graph.readFileInput("GrafoDFS.txt");
        graph.DFS();
        graph.printVertexType();
    }
}
