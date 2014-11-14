/**
 * Created by Maksim.Muzafarov on 11.11.14.
 * Project Week1_CountingInversions
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

class Graph {

    Map<Integer, Node> nodes = new TreeMap<Integer, Node>();

    public void addNode(Node node) {
        if (!nodes.containsValue(node)) {
            this.nodes.put(node.getLabel(), node);
        }
    }

    public Node getNode(int label) {
        return nodes.get(label);
    }

    public void reverse() {

    }
}

class Node {

    private int label;
    private List<Edge> edges = new ArrayList<Edge>();

    Node(int label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Node && this.label == ((Node) o).label;
    }

    public void addEdge(Node end, Boolean directed) {
        this.edges.add(new Edge(this, end, directed));
    }

    public int getLabel() {
        return label;
    }
}

class Edge {

    private boolean wellDirected;
    private Node begin;
    private Node end;

    Edge(Node begin, Node end) {
        this.begin = begin;
        this.end = end;
        this.wellDirected = true;
    }

    Edge(Node begin, Node end, Boolean wellDirected) {
        this.begin = begin;
        this.end = end;
        this.wellDirected = wellDirected;
    }

}

public class W4StronglyConnectedComponents {

    List<Integer>[] adjacentVertices = null;
    List<Integer>[] incomingVertices = null;

    public W4StronglyConnectedComponents(String filePath) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader(new File(filePath)));
        String s =in.readLine();
        adjacentVertices = new List[2000001]; // One vertex is wasted / unused
        incomingVertices = new List[2000001];

        Graph graph = new Graph();

        while(s!=null)
        {
            String[] s1 = s.split(" ");
            int vertex1 = Integer.parseInt(s1[0]) - 1;
            int vertex2 = Integer.parseInt(s1[1]) - 1;
            addEdge(vertex1, vertex2);
            // new code
            graph.addNode(new Node(vertex1));
            graph.addNode(new Node(vertex2));
            graph.getNode(vertex1).addEdge(graph.getNode(vertex2), true);

            s = in.readLine();
        }
        in.close();

    }

    private void addEdge(int vertex1, int vertex2){
        List<Integer> adjacentVertexList = adjacentVertices[vertex1];
        if(adjacentVertexList == null)
            adjacentVertexList = new ArrayList<Integer>();
        if(!adjacentVertexList.contains(vertex2)){
            adjacentVertexList.add(vertex2);
            adjacentVertices[vertex1] = adjacentVertexList;
        }

        List<Integer> incomingVertexList = incomingVertices[vertex2];
        if(incomingVertexList == null)
            incomingVertexList = new ArrayList<Integer>();
        if(!incomingVertexList.contains(vertex1)){
            incomingVertexList.add(vertex1);
            incomingVertices[vertex2] = incomingVertexList;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        W4StronglyConnectedComponents problem = new W4StronglyConnectedComponents("./resources/SCC.txt");

        problem.printTopComponents();

    }

    private void printTopComponents() {
        List<List<Integer>> stronglyConnectedComponents = (new StronglyConnectedComponents(adjacentVertices,incomingVertices)).getComponents();

        int[] componentSizes = new int[stronglyConnectedComponents.size()];

        int i=0;
        int totalSize = 0;
        for(List<Integer> component: stronglyConnectedComponents){
            componentSizes[i] = component.size();
            totalSize += componentSizes[i];
            i++;
        }

        Arrays.sort(componentSizes);

        System.out.println("Total Nodes from All SCCs = " + totalSize);

        for(i = componentSizes.length-1; i> componentSizes.length-6 && i>=0;i--)
            System.out.print(componentSizes[i]+",");

    }

}