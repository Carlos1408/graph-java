package data.structure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
//import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graph {
    private boolean directed;
    //private boolean weighted;
    private ListLinked<Vertex> vertexList;
    private Vertex[] vertexs;
    private int numVertexs;

    private boolean isConnected;
    private int componentConnected;

    public Graph(boolean directed)
    {
        this.directed = directed;
        isConnected = false;
        componentConnected = 0;
        vertexList = new ListLinked<>();
    }

    public Graph(boolean directed, int numVertexs)
    {
        this.directed = directed;
        this.vertexs = new Vertex[numVertexs];
        isConnected = false;
        componentConnected = 0;
    }

    public boolean isConnected() {
        BFS();
        return isConnected;
    }

    public int getComponentConnected() {
        return componentConnected;
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

    public void BFS(Vertex vertex)
    {
        ListLinked<Vertex> travelBFS = new ListLinked<>();
        //Queue<Vertex> queue = new Queue<>();
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(vertex);
        vertex.setStatus(State.VISITED);
        travelBFS.add(vertex);
        while(!queue.isEmpty())
        {
            //vertex = queue.remove().getData();
            vertex = queue.poll();
            ListLinked<Edge> lEdges = vertex.getEdges();
            Node<Edge> node = lEdges.getHead();
            while(node!=null)
            {
                Vertex opposite = node.getData().getV2();
                if(opposite.getState() == State.NOT_VISITED)
                {
                    queue.add(opposite);
                    opposite.setStatus(State.VISITED);
                    opposite.setJumps(vertex.getJumps()+1);
                    opposite.setParent(node.getData().getV1());
                    travelBFS.add(opposite);
                }
                node = node.getLink();
            }
            vertex.setStatus(State.PROCESSED);
        }
        Node<Vertex> temp = travelBFS.getHead();
        while (temp!=null) {
            System.out.print(temp.getData().getLabel()+"{"+temp.getData().getJumps()+"} +["+temp.getData().getParent().getLabel()+"]");
            temp = temp.getLink();
        }
    }

    public void BFS() {
        Node<Vertex> iterator = vertexList.getHead();
        isConnected = false;
        while(iterator != null) {
            Vertex vertex = iterator.getData();
            if(vertex.getState().compareTo(State.NOT_VISITED) == 0) {
                BFS(vertex);
                componentConnected ++;
                isConnected = componentConnected == 1;
            }
            iterator = iterator.getLink();
        }
    }

    public void shortPath(Vertex start, Vertex finish) {
        //BFS(start);
        DFS(start);
        Vertex parent = finish.getParent();
        while(parent != start.getParent()) {
            System.out.print(parent.getLabel()+"{"+parent.getJumps()+"} ");
            parent = parent.getParent();
        }
    }

    public void DFS(Vertex vertex) {
        ListLinked<Vertex> travelDFS = new ListLinked<>();
        Stack<Vertex> stack = new Stack<>();
        int time = 0;
        stack.add(vertex);
        vertex.setStatus(State.VISITED);
        vertex.setTimeIn(0);
        travelDFS.add(vertex);
        while(!stack.isEmpty()) {
            vertex = stack.pop();
            vertex.setTimeIn(time);
            ListLinked<Edge> lEdges = vertex.getEdges();
            Node<Edge> node = lEdges.getHead();
            while(node != null) {
                Vertex opposite = node.getData().getV2();
                if(opposite.getState() == State.NOT_VISITED) {
                    time++;
                    opposite.setStatus(State.VISITED);
                    opposite.setJumps(vertex.getJumps() + 1);
                    stack.add(opposite);
                    travelDFS.add(opposite);
                }
                node = node.getLink();
            }
            vertex.setStatus(State.PROCESSED);
            vertex.setTimeOut(time+1);
        }
        Node<Vertex> temp = travelDFS.getHead();
        while (temp!=null) {
            System.out.print(temp.getData().getLabel()+"{"+temp.getData().getTimeIn()+"; "+temp.getData().getTimeOut()+"} -> ");
            temp = temp.getLink();
        }
    }

    public void DFS() {
        Node<Vertex> iterator = vertexList.getHead();
        isConnected = false;
        while(iterator != null) {
            Vertex vertex = iterator.getData();
            if(vertex.getState().compareTo(State.NOT_VISITED) == 0) {
                DFS(vertex);
                componentConnected++;
                isConnected = componentConnected == 1;
            }
            iterator = iterator.getLink();
        }
    }

    public void printGraph()
    {
        ListLinked<Edge> edgesVertex = new ListLinked<>();
        for(int i=0; i < vertexs.length; i++)
        {
            System.out.print("\nVERTEX= "+vertexs[i].getLabel()+"| LINKS");
            edgesVertex = vertexs[i].getEdges();
            for(int j=0; j<edgesVertex.size(); j++)
                System.out.print(" -> "+edgesVertex.getNode(j).getData().getV2().getLabel()+"("+edgesVertex.getNode(j).getData().getWeight()+")");
        }
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
        String line="";
        try
        {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            Pattern pattern = Pattern.compile("size\\s*=\\s*(\\d+)");
            line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            String strSize = matcher.group(1);
            vertexs = new Vertex[Integer.parseInt(strSize)];
            //Obteniendo las lineas de informacion de vertices
            while(!((line = scanner.nextLine()).equals(";")))
            {
                pattern = Pattern.compile("(\\d+)\\s*=\\s*(.+)");
                matcher = pattern.matcher(line);
                //boolean resp = matcher.find();
                if(matcher.find())
                {
                    Vertex vertex = new Vertex(matcher.group(2));
                    addVertex(vertex);
                    vertexs[Integer.parseInt(matcher.group(1))] = vertex;
                }
            }
            //Obteniendo las lineas de informacion de aristas
            while(!(line = scanner.nextLine()).equals(";"))
            {
                pattern = Pattern.compile("\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");
                matcher = pattern.matcher(line);
                //boolean resp = matcher.find();
                if(matcher.find())
                {
                    int posV1 = Integer.parseInt(matcher.group(1));
                    int posV2 = Integer.parseInt(matcher.group(2));
                    double weight = Double.parseDouble(matcher.group(3));
                    Vertex v1 = vertexs[posV1];
                    Vertex v2 = vertexs[posV2];
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
        /*Vertex LaPaz = new Vertex("La Paz");
        Vertex Cochabamba = new Vertex("Cochabamba");
        Vertex SantaCruz = new Vertex("Santa Cruz");
        Vertex Riberalta = new Vertex("Riberalta");

        LaPaz.addEdge(new Edge(LaPaz, Cochabamba));
        LaPaz.addEdge(new Edge(LaPaz, SantaCruz));
        LaPaz.addEdge(new Edge(LaPaz, Riberalta));
        graph.addVertex(LaPaz);
        graph.addVertex(Cochabamba);
        graph.addVertex(SantaCruz);
        graph.addVertex(Riberalta);*/

        graph.readFileInput("bolivia.txt");
        graph.printGraph();
    }
}
