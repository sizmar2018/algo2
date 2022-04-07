import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.GraphGenerator;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Graph g = GraphGenerator.simple(5, 5);
        System.out.println(g);
        System.out.println("Greedy Algorithm solution :");
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(3, g);
        greedyAlgorithm.colorGraph();
        createGraph();
    }


    private static void createGraph() {

        Path path = Paths.get("CA-GrQc.txt");
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(path);
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Graph g = new Graph();
    }
}
