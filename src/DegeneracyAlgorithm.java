import edu.princeton.cs.algs4.Graph;

import java.util.*;

public class DegeneracyAlgorithm {

    private final Graph g;

    public DegeneracyAlgorithm(Graph graph) {
        this.g = graph;
    }

    public int getKDegenerate(){
        int[] vDegree = new int[g.V()];
        Set<Integer> vDeleted = new HashSet<>();
        int maxDegree = Integer.MIN_VALUE;
        for (int i = 0; i < g.V(); i++){
            int degree = g.degree(i);
            vDegree[i] = degree;
            if (degree > maxDegree){
                maxDegree = degree;
            }
        }
        int[] vCores = new int[g.V()];
        HashSet<Integer>[] vectorsByDegree = new HashSet[maxDegree + 1];
        for(int i = 0; i < maxDegree + 1; i++){
            vectorsByDegree[i] = new HashSet<>();
        }
        for(int i = 0; i < g.V(); i++){
            vectorsByDegree[g.degree(i)].add(i);
        }

        int k = 0;
        for(int i = 0; i < g.V(); i++){
            int j = getMinDegree(vectorsByDegree);
            k = Math.max(j, k);
            int v = getVertexFromSet(vectorsByDegree[j]);
            vectorsByDegree[j].remove(v);
            vDeleted.add(v);
            vCores[v] = k;
            vDegree[v] = 0;
            for(int w: g.adj(v)){
                if(!vDeleted.contains(w)){
                    int wDegree = vDegree[w];
                    vectorsByDegree[wDegree].remove(w);
                    vectorsByDegree[wDegree - 1].add(w);
                    vDegree[w] = wDegree - 1;
                }
            }
        }
        return k;
    }

    private int getVertexFromSet(HashSet<Integer> vertexes) {
        for (int v: vertexes){
            return v;
        }
        throw new RuntimeException("Empty set");
    }

    private int getMinDegree(HashSet<Integer>[] vectorsByDegree) {
        int j = 0;
        while (j < vectorsByDegree.length){
            if(!vectorsByDegree[j].isEmpty()){
                break;
            }
            j += 1;
        }
        return j;
    }
}
