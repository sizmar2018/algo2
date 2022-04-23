import edu.princeton.cs.algs4.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class KCoresAlgorithm {

    public int[] getCores(Graph g){
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
            vDeleted.add(v);
            vCores[v] = k;
            vDegree[v] = 0;
            for(int w: g.adj(v)){
                if(!vDeleted.contains(w)){
                    int wDegree = vDegree[w];
                    vectorsByDegree[wDegree].remove(Integer.valueOf(w));
                    vectorsByDegree[wDegree - 1].add(w);
                    vDegree[w] = wDegree - 1;
                }
            }
        }
        return vCores;
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
