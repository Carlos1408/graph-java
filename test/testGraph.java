package test;

import data.structure.*;

public class testGraph {
    public static void main(String[] args) {
        Graph graph = new Graph(true);
        graph.readFileInput("Grafo1.txt");
        //Vertex ver = graph.getVertexList().getHead().getData();
        //Vertex ver = graph.getVertexs()[0];
        //System.out.println(ver);
        //graph.printGraph();
        graph.BFS(graph.getVertexList().getHead().getData());
        //graph.shortPath(graph.getVertexList().getHead().getData(), graph.getVertexList().getNode(2).getData());
    }
}
