package test;
import data.structure.*;

public class TestColors {
    public static void main(String[] args) {
        Graph graph = new Graph(false);
        graph.readFileInput("GrafoColoreado2.txt");
        graph.paintGraph();
    }
}
