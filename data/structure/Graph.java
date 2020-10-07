package data.structure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graph {
    private boolean directed;
    //private boolean weighted;
    private ListLinked<Vertex> vertexList;
    private Vertex[] vertexs;
    private int numVertexs;

    public Graph(boolean directed)
    {
        this.directed = directed;
        vertexList = new ListLinked<>();
    }

    public Graph(boolean directed, int numVertexs)
    {
        this.directed = directed;
        vertexs = new Vertex[numVertexs];
    }

    public boolean getDirected()
    {
        return directed;
    }

    public ListLinked<Vertex> getVertexList()
    {
        return vertexList;
    }

    public Vertex[] getVertexs()
    {
        return vertexs;
    }

    public int getNumVertex()
    {
        return numVertexs;
    }

    public void addVertex(Vertex vertex)
    {
        vertexList.add(vertex);
    }

    public void addEdge(Vertex v1, Vertex v2, double weight)
    {
        Edge edge = new Edge(v1, v2, weight);
        v1.addEdge(edge);
        if(!directed)
        {
            Edge edge2 = new Edge(v2, v1, weight);
            v2.addEdge(edge2);
        }
    }

    public void readFileInput(String filename)
    {
        String path = System.getProperty("user.dir")+"\\input\\"+filename;
        try
        {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            String line="";
            Pattern pattern = Pattern.compile("size\\s*=\\s*(\\d+)");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            String strSize = matcher.group(1);
            vertexs = new Vertex[Integer.parseInt(strSize)];
            //Obteniendo las lineas de informacion de vertices
            while(!(line = scanner.nextLine()).equals(";"))
            {
                pattern = Pattern.compile("(\\d+)\\s*=\\s*(.+)");
                matcher = pattern.matcher(line);
                //boolean resp = matcher.find();
                if(!matcher.find())
                {
                    Vertex vertex = new Vertex(matcher.group(2));
                    addVertex(vertex);
                    vertexs[Integer.parseInt(matcher.group(1))] = vertex;
                }
            }
            //Obteniendo las lineas de informacion de aristas
            while(!(line = scanner.nextLine()).equals(";"))
            {
                pattern = Pattern.compile("\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+,?\\d*)\\s*\\)");
                matcher = pattern.matcher(line);
                //boolean resp = matcher.find();
                if(!matcher.find())
                {
                    int posV1 = Integer.parseInt(matcher.group(1));
                    int posV2 = Integer.parseInt(matcher.group(2));
                    double weight = Double.parseDouble(matcher.group(3));
                    Vertex v1 = vertexs[posV1];
                    Vertex v2 = vertexs[posV2];
                    //Edge edge = new Edge(v1, v2, weight);
                    addEdge(v1, v2, weight);
                }
            }
            scanner.close();
        }catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(false);
        Vertex LaPaz = new Vertex("La Paz");
        Vertex Cochabamba = new Vertex("Cochabamba");
        Vertex SantaCruz = new Vertex("Santa Cruz");
        Vertex Riberalta = new Vertex("Riberalta");

        LaPaz.addEdge(new Edge(LaPaz, Cochabamba));
        LaPaz.addEdge(new Edge(LaPaz, SantaCruz));
        LaPaz.addEdge(new Edge(LaPaz, Riberalta));
        graph.addVertex(LaPaz);
        graph.addVertex(Cochabamba);
        graph.addVertex(SantaCruz);
        graph.addVertex(Riberalta);

        graph.readFileInput("bolivia.txt");
    }
}