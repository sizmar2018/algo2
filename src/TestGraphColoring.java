import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.GraphGenerator;

public class TestGraphColoring {
    public static void main(String[] args) {
        Graph g = GraphGenerator.simple(5, 10);
        System.out.println(g);
        DegeneracyAlgorithm degeneracyAlgorithm = new DegeneracyAlgorithm(g);
        int k =degeneracyAlgorithm.getKDegenerate()+1;
        System.out.println("Greedy Algorithm solution :" + k);
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(1+degeneracyAlgorithm.getKDegenerate(), g, degeneracyAlgorithm.getOrder());
        Integer[] result = greedyAlgorithm.colorGraph();
        if(checkColorGraph(g,result)){
            System.out.println("success");
        }else{
            System.out.println("Failed");
        }
    }

    public static boolean checkColorGraph(Graph unColoredGraph, Integer[] coloredGraph) {

        // tableau contenant la correspondence entre les sommets du graphe(les indices) et la couleur(valeur à l'indice) qui lui est associé
        int nbVertex = unColoredGraph.V();

        for (int v = 0; v < nbVertex; v++) {
            for (Integer adjVertex : unColoredGraph.adj(v)) {
                Integer adjVertexColor = coloredGraph[adjVertex];
                if (adjVertexColor.intValue() == coloredGraph[v].intValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
