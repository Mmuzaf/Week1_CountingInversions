import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class W3MinimumCutProblem {

    public static void main(String[] args) throws IOException {

        //Maximum of input vertexes
        int finalMinimumCut = 200;

        for(int j = 0; j < 10; j++) {
            BufferedReader buff = new BufferedReader(new FileReader(new File("./resources/kargerMinCut.txt")));
            List<Vertex> vertexes = new ArrayList<Vertex>();

            for(int i = 0; i < 200; i++)
                vertexes.add(new Vertex());

            String line = buff.readLine();
            while(line != null)
            {
                String[] vertexIndices = line.split("\\s+");
                for(int i = 1; i < vertexIndices.length; i++) {
                    vertexes.get(Integer.parseInt(vertexIndices[0]) - 1).
                            addAdjacentVertex(
                                    vertexes.get(Integer.parseInt(vertexIndices[i]) - 1)
                            )
                    ;
                }
                line = buff.readLine();
            }
            buff.close();

            RandomizedContractionUtility randomizedContractionUtility =
                    new RandomizedContractionUtility(vertexes);

            int minimumCut = randomizedContractionUtility.minimumCut();
            System.out.println("Current Cut = " + minimumCut);
            if(finalMinimumCut > minimumCut)
                finalMinimumCut = minimumCut;
        }
        System.out.println("Minimum Cut = " + finalMinimumCut);
    }
}
