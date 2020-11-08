package test;

import data.structure.Graph;
import data.structure.ListLinked;
import data.structure.Vertex;
import data.structure.Node;

public class TestArticulation {
    public static void main(String[] args) {
        Graph graph = new Graph(false);
        ListLinked <Vertex> articulations;
        Node<Vertex> node;
        graph.readFileInput("Grafo_con_articulacion.txt");
        articulations = graph.getVertexArticulationList();
        node = articulations.getHead();
        while(node!=null) {
            System.out.print("\nVertice articulado: "+node.getData().getLabel()+" -> Conexiones: ");
            graph.printVertexConections(node.getData());
            node = node.getLink();
        }

        System.out.println("\n");

        graph = new Graph(false);
        graph.readFileInput("Grafo3.txt");
        articulations = graph.getVertexArticulationList();
        node = articulations.getHead();
        while(node!=null) {
            System.out.print("\nVertice articulado: "+node.getData().getLabel()+" -> Conexiones: ");
            graph.printVertexConections(node.getData());
            node = node.getLink();
        }
    }
}