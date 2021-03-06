/**
 * Created by Maksim.Muzafarov on 11.11.14.
 * Project Week1_CountingInversions
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StronglyConnectedComponents {

    List<Integer>[] adjacentVertices = null;
    List<Integer>[] incomingVertices = null;

    // Global variables for DFS-Loop
    private int finishingTime = 0;
    private int[] finishingTimes = null;
    private List<List<Integer>> components = null;
    boolean[] explored = null;

    public StronglyConnectedComponents(List<Integer>[] adjacentVertices,List<Integer>[] incomingVertices){
        this.adjacentVertices = adjacentVertices;
        this.incomingVertices = incomingVertices;
        finishingTimes = new int[adjacentVertices.length];
        explored = new boolean[adjacentVertices.length];
    }

    public List<List<Integer>> getComponents() {

        // Run DFS-Loop for finishing times of vertices
        // This is run over the inverted graph
        // the order of vertices is from highest to lowest



        List<Integer> order = new ArrayList<Integer>();
        for(int i=adjacentVertices.length-1;i>=0; i--)
            order.add(i);
        DFSLoop(order, incomingVertices); // inverted graph



        // Compute new order based on finishing times
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<finishingTimes.length; i++)
            map.put(finishingTimes[i], i);

        order = new ArrayList<Integer>();
        for(int i=map.size();i>0; i--){
            order.add(map.get(i));
            //if(i>map.size()-100)
            //System.out.print(map.get(i) + " ");
        }



        // Call DFS Loop again to compute SCCs
        components = new ArrayList();
        explored = new boolean[adjacentVertices.length];
        DFSLoop(order, adjacentVertices);





        return components;
    }

    private void DFSLoop(List<Integer> order,
                         List<Integer>[] adjacentVertices) {

        for(Integer vertex: order){
            if(!explored[vertex]){
                List<Integer> currentComponent = null;
                if(components!=null){
                    currentComponent = new ArrayList<Integer>();
                    components.add(currentComponent);
                    // The vertices get added to this component in the DFS call below
                }
                DFS(adjacentVertices, vertex, currentComponent);
            }
        }

    }

    private void DFS(List<Integer>[] adjacentVertices,
                     Integer vertex, List<Integer> currentComponent) {
        explored[vertex] = true;
        if(currentComponent != null)
            currentComponent.add(vertex);

        List<Integer> adjacentVertexList = adjacentVertices[vertex];
        if(adjacentVertexList!=null){
            for(int i=0; i< adjacentVertexList.size(); i++){
                Integer adjacentVerex= adjacentVertexList.get(i);
                if(!explored[adjacentVerex])
                    DFS(adjacentVertices, adjacentVerex, currentComponent);
            }
        }
        finishingTime++;
        finishingTimes[vertex] = finishingTime;

    }

}