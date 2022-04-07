import edu.princeton.cs.algs4.Graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GreedyAlgorithm {

    private final Graph unColoredGraph;
    private final int nbMaxColor;
    private List<Integer> colors;

    public GreedyAlgorithm(int nbMaxColor, Graph graph) {
        this.nbMaxColor = nbMaxColor;
        this.unColoredGraph = graph;
        this.initializeColor();
    }

    public void colorGraph() {

        // Dictionary vertex-color
        Map<Integer, Integer> coloredGraph = new HashMap<>();
        //assigner la première couleur au premier sommet
        coloredGraph.put(0, 0);
        System.out.println("Vertex "+0 + " is colored with " + 0);
        for (int v = 1; v < unColoredGraph.V(); v++) {

            // Operation add complexité O(ln N) ou n est le nombre d'élement dans l'ensemble
            Set<Integer> colorAdjVertices = new TreeSet<>();
            Iterable<Integer> adjVertices = unColoredGraph.adj(v);

            for (Integer adjVertex : adjVertices) {
                Object adjVertexColorObject = coloredGraph.get(adjVertex);
                if (adjVertexColorObject != null) {
                    int adjVertexColor = Integer.parseInt(adjVertexColorObject.toString());
                    colorAdjVertices.add(adjVertexColor);
                }
            }

            int assignedColor = checkFirstFreeColor(colorAdjVertices);
            coloredGraph.put(v, assignedColor);
            System.out.println("Vertex "+v + " is colored with " + assignedColor);
        }

    }

    private void initializeColor() {
        this.colors = IntStream.rangeClosed(0, nbMaxColor - 1).boxed().collect(Collectors.toList());
    }

    private int checkFirstFreeColor(Set<Integer> colorAdjVertices) {
        int colorIdx = 0;
        for (int c : colorAdjVertices) {
            int currentColor = this.colors.get(colorIdx);
            if (currentColor != c) {
                return currentColor;
            }
            colorIdx++;
        }
        return this.colors.get(colorIdx);
    }
}
