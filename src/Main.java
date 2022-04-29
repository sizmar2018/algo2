import edu.princeton.cs.algs4.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        Graph graph;
        if(args.length == 3){
            graph = createGraph(args[0],Boolean.parseBoolean(args[1]),0,args[2]);
        } else {
            graph = createGraph(args[0],Boolean.parseBoolean(args[1]),Integer.parseInt(args[2]), args[3]);
        }

        System.out.println(graph.V() + " - " + graph.E());
        DegeneracyAlgorithm degeneracyAlgorithm = new DegeneracyAlgorithm(graph);
        long startTime = System.currentTimeMillis();
        int k = degeneracyAlgorithm.getKDegenerate();
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("Degeneracy of the graph is: " + k);
        System.out.println("It took " + duration + " ms");
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(1 + k, graph, degeneracyAlgorithm.getOrder());
        startTime = System.currentTimeMillis();
        Integer[] coloredGraph = greedyAlgorithm.colorGraph();
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("Greedy coloring took " + duration + " ms");
        KCoresAlgorithm kCoresAlgorithm = new KCoresAlgorithm();
        startTime = System.currentTimeMillis();
        int[] cores = kCoresAlgorithm.getCores(graph);
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("KCores Algorithm took " + duration + " ms");
    }


    private static Graph createGraph(String fileName, boolean nbVertexInFile, int nbVertex, String separator) {
        try {
            Path path = Paths.get(fileName);
            BufferedReader reader = Files.newBufferedReader(path);
            if(nbVertexInFile){
                String line = reader.readLine();
                nbVertex = Integer.parseInt(line);
                line = reader.readLine();
            }
            Graph graph = new Graph(nbVertex);
            String line = reader.readLine();
            while (line != null) {
                String[] vectors = line.split(separator);
                int v1 = Integer.parseInt(vectors[0]);
                int v2 = Integer.parseInt(vectors[1]);
                graph.addEdge(v1, v2);
                line = reader.readLine();
            }
            return graph;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
