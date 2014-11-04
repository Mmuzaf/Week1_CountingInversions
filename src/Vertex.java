import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private List<Vertex> adjacentVertexes;

    Vertex () {
        this.adjacentVertexes = new ArrayList<Vertex>();
    }

    Vertex (List<Vertex> adjacentVertexes) {
        this.adjacentVertexes = adjacentVertexes;
    }

    public void merge(Vertex vertex){
        //Remove self-loops
        while(this.adjacentVertexes.remove(vertex));
        // Add the arrayList in the current vertex to the array list from incoming vertex
        for(Vertex n : vertex.getAdjacentVertexes()){
            if(n == this)
                continue; // skip self-loops
            n.addAdjacentVertex(this);
            while(n.getAdjacentVertexes().remove(vertex));
            this.addAdjacentVertex(n);
        }
    }

    public List<Vertex> getAdjacentVertexes() {
        return adjacentVertexes;
    }

    public void addAdjacentVertex(Vertex adjacentVertex) {
        adjacentVertexes.add(adjacentVertex);
    }

//    public Vertex copy() {
//        List<Vertex> list = new ArrayList<Vertex>(this.adjacentVertexes.size());
//        for (Vertex v : this.adjacentVertexes)
//            list.add(v.copy());
//        return new Vertex(list);
//    }
}