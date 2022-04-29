import edu.princeton.cs.algs4.Graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GreedyAlgorithm {

    private final Graph unColoredGraph;
    private final int nbMaxColor;
    private final List<Integer> vOrder;

    public GreedyAlgorithm(int nbMaxColor, Graph graph, LinkedList<Integer> vOrder) {
        this.checkArguments(nbMaxColor <= 0, graph == null);
        this.nbMaxColor = nbMaxColor;
        this.unColoredGraph = graph;
        this.vOrder = vOrder;
    }

    private void checkArguments(boolean firstCondition, boolean secondCondition) {
        if (firstCondition || secondCondition) throw new IllegalArgumentException("Les arguments ne sont pas valides");
    }

    public Integer[] colorGraph() {
        // Tableau contenant la correspondance entre les sommets du graphe(indices) et la couleur(valeur à des indices) qui lui est associé
        Integer[] coloredGraph = new Integer[vOrder.size()];

        for (int v : vOrder) {
            //Tableau qui permet de savoir si une couleur(indices) est utilisé par un sommet adjacent.
            boolean[] isUsed = new boolean[nbMaxColor];
            //parcours les sommets adjacents au sommet courant pour récupérer la couleur utilisée par ces sommets adjacents
            for (Integer adjVertex :  this.unColoredGraph.adj(v)) {
                Integer adjVertexColor = coloredGraph[adjVertex];
                if (adjVertexColor != null) {
                    isUsed[adjVertexColor] = true;
                }
            }
            int assignedColor = findFirstFreeColor(isUsed);
            coloredGraph[v] = assignedColor;

        }
        return coloredGraph;
    }

    /**
     * Trouve la première couleur disponible non utilisée par un sommet adjacent
     *
     * @param colorAdjVertices Ensemble de couleur utilisée par les sommets adjacents triée par ordre naturel
     * @return la premiere couleur non utilisée
     */
    private int findFirstFreeColor(boolean[] colorAdjVertices) {

        for (int c = 0; c < this.nbMaxColor; c++) {
            if (!colorAdjVertices[c]) {
                return c;
            }
        }
        throw new RuntimeException("Ce graphe n'est pas coloriable avec k+1 couleurs");
    }

    public static void main(String[] args) {

    }
}
