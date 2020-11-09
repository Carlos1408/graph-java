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
    private int time;

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
                    //System.out.println(Edge: "("+vertex.getLabel()+", "+opposite.getLabel()+")");
                }
                node = node.getLink();
            }
            vertex.setStatus(State.PROCESSED);
        }
        /*Node<Vertex> temp = travelBFS.getHead();
        while (temp!=null) {
            System.out.print(temp.getData().getLabel()+"{"+temp.getData().getJumps()+"} +["+temp.getData().getParent().getLabel()+"]");
            temp = temp.getLink();
        }*/
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
        BFS(start);
        //DFS(start);
        Vertex parent = finish.getParent();
        while(parent != start.getParent()) {
            System.out.print(parent.getLabel()+"{"+parent.getJumps()+"} ");
            parent = parent.getParent();
        }
    }

    public void DFS(Vertex vertex) {
        ListLinked<Vertex> travelDFS = new ListLinked<>();
        Stack<Vertex> stack = new Stack<>();
        stack.add(vertex);
        vertex.setStatus(State.VISITED);
        vertex.setTimeIn(0);
        travelDFS.add(vertex);
        while(!stack.isEmpty()) {
            vertex = stack.pop();
            ListLinked<Edge> lEdges = vertex.getEdges();
            Node<Edge> node = lEdges.getHead();
            while(node != null) {
                Vertex opposite = node.getData().getV2();
                if(opposite.getState() == State.NOT_VISITED) {
                    opposite.setStatus(State.VISITED);
                    stack.add(opposite);
                    travelDFS.add(opposite);
                } else if(opposite.getState().compareTo(State.PROCESSED)==0)
                    node.getData().setType(Type.LATER);
                node = node.getLink();
            }
            vertex.setStatus(State.PROCESSED);
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

    public void DFSTimed(Vertex vertex, ListLinked<Vertex> travelDFS) {
        vertex.setStatus(State.VISITED);
        travelDFS.add(vertex);
        ListLinked<Edge> lEdges = vertex.getEdges();
        vertex.setTimeIn(time);
        time++;
        Node<Edge> node = lEdges.getHead();
        while(node != null) {
            Vertex opposite = node.getData().getV2();
            if(opposite.getState() == State.NOT_VISITED)
                DFSTimed(opposite, travelDFS);
            node = node.getLink();
        }
        vertex.setStatus(State.PROCESSED);
        vertex.setTimeOut(time);
        time++;
    }

    private boolean BFSArticulated() {
        Node<Vertex> iterator = vertexList.getHead();
        int components = 0;
        while(iterator != null) {
            Vertex vertex = iterator.getData();
            if(vertex.getState().compareTo(State.NOT_VISITED) == 0) {
                BFS(vertex);
                components++;
                //System.out.println("componentes: "+components);
            }
            iterator = iterator.getLink();
        }
        if(components==1)
            return false;
        else
            return true;
    }

    public void printVertexConections(Vertex vertex) {
        Node<Edge> iterator = vertex.getEdges().getHead();
        while(iterator!=null) {
            System.out.print("Vertex["+iterator.getData().getV2().getLabel()+"] - ");
            iterator = iterator.getLink();
        }
    }

    private void restoreVertexStatus() {
        Node<Vertex> iterator = vertexList.getHead();
        while(iterator!=null) {
            iterator.getData().setStatus(State.NOT_VISITED);
            iterator = iterator.getLink();
        }
    }

    public void printStatus() {
        Node<Vertex> iterator = vertexList.getHead();
        while(iterator!=null) {
            System.out.println(iterator.getData().getLabel()+"{"+iterator.getData().getState()+"}");
            iterator = iterator.getLink();
        }
    }

    public ListLinked<Vertex> getVertexArticulationList() {
        Node<Vertex> iterator = vertexList.getHead();
        Vertex vertex = null;
        Boolean articulationExists = false;
        ListLinked<Vertex> list = new ListLinked<>();
        while(iterator!=null) {
            vertex = iterator.getData();
            vertex.setStatus(State.DELETED);
            //printStatus();
            articulationExists = BFSArticulated();
            iterator = iterator.getLink();
            //printStatus();
            restoreVertexStatus();
            if(articulationExists)
                list.add(vertex);
        }
        return list;
    }

    public void DFSTimed() {
        time = 0;
        Node<Vertex> iterator = vertexList.getHead();
        ListLinked<Vertex> travelDFS = new ListLinked<>();
        while(iterator != null) {
            Vertex vertex = iterator.getData();
            if(vertex.getState().compareTo(State.NOT_VISITED) == 0)
                DFSTimed(vertex, travelDFS);
            iterator = iterator.getLink();
        }
        
        iterator = travelDFS.getHead();
        while(iterator != null) {
            Vertex vertex = iterator.getData();
            System.out.print(vertex.getLabel()+"{"+vertex.getTimeIn()+"; "+vertex.getTimeOut()+"}-> ");
            iterator = iterator.getLink();
        }
    }

    public void printTimes() {
        Node<Vertex> node = vertexList.getHead();
        Vertex vertex;
        while(node != null) {
            vertex = node.getData();
            System.out.println(vertex.getLabel()+"{"+vertex.getTimeIn()+"; "+vertex.getTimeOut()+"}");
            node = node.getLink();
        }
    }

    public void dijkstra(Vertex vertex) {
        ListLinked<Vertex> priorityList = new ListLinked<>();
        vertex.setStatus(State.VISITED);
        vertex.setDistance(0);
        vertex.setParent(null);
        priorityList.add(vertex);
        while(!priorityList.isEmpty()) {
            //printPriorityList(priorityList);

            vertex = getMinimunVertexAcumulated(priorityList);
/*
            if(vertex.getParent()==null)
                System.out.println(" =>"+vertex.getLabel()+"["+vertex.getDistance()+", null]"+"");
            else
                System.out.println(" =>"+vertex.getLabel()+"["+vertex.getDistance()+", "+vertex.getParent().getLabel()+"]"+"");
*/
            ListLinked<Edge> lEdges = vertex.getEdges();
            Node<Edge> node = lEdges.getHead();
            while(node!=null) {
                Vertex opposite = node.getData().getV2();
                if(opposite.getState().compareTo(State.NOT_VISITED)==0) {
                    opposite.setStatus(State.VISITED);
                    opposite.setParent(vertex);
                    opposite.setDistance(vertex.getDistance()+node.getData().getWeight());
                    priorityList.add(opposite);
/*
                    if(opposite.getParent()==null)
                        System.out.println(opposite.getLabel()+"["+opposite.getDistance()+", null]"+"");
                    else
                        System.out.println(opposite.getLabel()+"["+opposite.getDistance()+", "+opposite.getParent().getLabel()+"]"+"");
*/                        
                }
                node = node.getLink();
            }
        }
        
    }

    public void dijkstra() {
        Node<Vertex> iterator = vertexList.getHead();
        isConnected = false;
        while(iterator != null) {
            Vertex vertex = iterator.getData();
            if(vertex.getState().compareTo(State.NOT_VISITED) == 0)
                dijkstra(vertex);
            iterator = iterator.getLink();
        }
    }

    public void printDijkstra() {
        Node<Vertex> node = vertexList.getHead();
        Vertex vertex;
        while(node != null) {
            vertex = node.getData();
            if(vertex.getParent()==null)
                System.out.println("Vertex: "+vertex.getLabel()+" [Distance: "+vertex.getDistance()+", Parent: null]");
            else
                System.out.println("Vertex: "+vertex.getLabel()+" [Distance: "+vertex.getDistance()+"; Parent: "+vertex.getParent().getLabel()+"]");
            node = node.getLink();
        }
    }

    private void printPriorityList(ListLinked<Vertex> list) {
        Node<Vertex> node = list.getHead();
        Vertex vertex;
        System.out.println("\n");
        while(node != null) {
            vertex = node.getData();
                System.out.print(vertex.getLabel()+" | ");
            node = node.getLink();
        }
    }

    private Vertex getMinimunVertexAcumulated(ListLinked<Vertex> list) {
        Node<Vertex> node = list.getHead();
        Vertex vertex = node.getData();
        Node<Vertex> previous = null;
        Vertex vertexMin = vertex;
        while(node!=null) {
            vertex = node.getData();
            if(vertexMin.getDistance()>vertex.getDistance())
                vertexMin = vertex;
            node = node.getLink();
        }
        node = list.getHead();
        while(node!=null) {
            if(node.getData() == vertexMin) {
                if(node == list.getHead())
                    list.removeHead();
                else {
                    previous.setLink(node.getLink());
                }
            }
            previous = node;
            node = node.getLink();
        }
        return vertexMin;
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
