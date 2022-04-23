import edu.princeton.cs.algs4.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Graph graph = createGraph();
        DegeneracyAlgorithm degeneracyAlgorithm = new DegeneracyAlgorithm(graph);
        long startTime = System.currentTimeMillis();
        int k = degeneracyAlgorithm.getKDegenerate();
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("Degeneracy of the graph is: "+k);
        System.out.println("It took "+duration+" ms");
//        KCoresAlgorithm kCoresAlgorithm = new KCoresAlgorithm(graph);
//        int[] cores = kCoresAlgorithm.getCores();
//        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(k+1,graph);
//        greedyAlgorithm.colorGraph();
    }


    private static Graph createGraph() {
        try {
            Path path = Paths.get("facebook_combined.txt");
            Graph graph = new Graph(4039);
            BufferedReader reader = Files.newBufferedReader(path);
            String line = reader.readLine();
            while (line != null){
                String[] vectors = line.split(" ");
                int v1 = Integer.parseInt(vectors[0]);
                int v2 = Integer.parseInt(vectors[1]);
                graph.addEdge(v1,v2);
                line = reader.readLine();
            }
            return graph;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
