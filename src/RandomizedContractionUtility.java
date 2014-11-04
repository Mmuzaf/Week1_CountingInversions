import java.util.ArrayList;
import java.util.List;

public class RandomizedContractionUtility {

    private List<Vertex> vertexes;

    public RandomizedContractionUtility(List<Vertex> vertexes) {
        this.vertexes = vertexes;
    }

    public int minimumCut() {
        if(vertexes.size() == 2){
            return vertexes.get(0).getAdjacentVertexes().size();
        }
        int firstVertexInd = Utils.randomInt(0, vertexes.size() - 1);
        // Pick random left node
        Vertex leftVertex = vertexes.get(firstVertexInd);
        int secondVertexInd = Utils.randomInt(0, leftVertex.getAdjacentVertexes().size() - 1);
        // Pick random right node from the list of adjacent vertices
        Vertex rightVertex = leftVertex.getAdjacentVertexes().get(secondVertexInd);
        leftVertex.merge(rightVertex);
        vertexes.remove(rightVertex);
        // Call itself
        return minimumCut();
    }

}