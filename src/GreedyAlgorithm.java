import edu.princeton.cs.algs4.Graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GreedyAlgorithm {

    private final Graph unColoredGraph;
    private final int nbMaxColor;
    private final List<Integer> colors;

    public GreedyAlgorithm(int nbMaxColor, Graph graph) {
        this.checkArguments(nbMaxColor <= 0, graph == null);

        this.nbMaxColor = nbMaxColor;
        this.unColoredGraph = graph;
        this.colors = this.initializeColor();
    }

    private void checkArguments(boolean firstCondition, boolean secondCondition) {
        if (firstCondition || secondCondition) throw new IllegalArgumentException("Les arguments ne sont pas valides");
    }



    public void  colorGraph() {


        // Dictionnaire contenant la correspondence entre les sommets du graphe et la couleur qui lui est associé
        Map<Integer, Integer> coloredGraph = new HashMap<>();

        for (int v = 0; v < unColoredGraph.V(); v++) {
            // TreeSet : Operation insertion complexité O(ln N) ou N est le nombre d'éléments dans l'ensemble.
            // Elements triés par ordre naturel(Arbre binaire balancé rouge noir)
            Set<Integer> colorAdjVertices = new TreeSet<>();

            Iterable<Integer> adjVertices = this.unColoredGraph.adj(v);

            //parcours les sommets adjacents au sommet courant pour récupérer la couleur utilisée par ces sommets adjacents
            for (Integer adjVertex : adjVertices) {
                Integer adjVertexColor = coloredGraph.get(adjVertex);
                if (adjVertexColor != null) {
                    colorAdjVertices.add(adjVertexColor);
                }
            }

            int assignedColor = findFirstFreeColor(colorAdjVertices);
            coloredGraph.put(v, assignedColor);
            System.out.println("Vertex " + v + " is colored with " + assignedColor);
        }

    }

    /**
     * Créer un tableau avec les k+1 couleur nécessaire pour colorier le graphe
     *
     * @return le tableau avec les couleurs
     */
    private List<Integer> initializeColor() {
        return IntStream.rangeClosed(0, this.nbMaxColor - 1).boxed().collect(Collectors.toList());
    }

    /**
     * Trouve la première couleur disponible non utilisée par un sommet adjacent
     *
     * @param colorAdjVertices Ensemble de couleur utilisée par les sommets adjacents triée par ordre naturel
     * @return la premiere couleur non utilisée
     */
    private int findFirstFreeColor(Set<Integer> colorAdjVertices) {
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
