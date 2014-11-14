import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

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

    public Vertex copy() {
        List<Vertex> list = new ArrayList<Vertex>(this.adjacentVertexes.size());
        if (!VertexCopyHolder.getInstance().containsKey(this))
            VertexCopyHolder.getInstance().put(this, Boolean.TRUE);
        for (Vertex v : this.adjacentVertexes) {
            if (!VertexCopyHolder.getInstance().containsKey(v)) {
                list.add(v.copy());
            } else {
                VertexCopyHolder.getInstance().put(v, Boolean.TRUE);
            }
        }
        return new Vertex(list);
    }
}

class VertexCopyHolder extends IdentityHashMap<Vertex, Boolean> {
    private static volatile VertexCopyHolder instance;

    public static VertexCopyHolder getInstance() {
        VertexCopyHolder localInstance = instance;
        if (localInstance == null) {
            synchronized (VertexCopyHolder.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new VertexCopyHolder();
                }
            }
        }
        return localInstance;
    }
}