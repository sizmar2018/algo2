import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class DegeneracyAlgorithm {

    private final Graph G;
    public LinkedList<Integer> vOrder = new LinkedList<>();

    public DegeneracyAlgorithm(Graph graph) {
        this.G = graph;
    }

    public int getKDegenerate(){
        int[] vDegree = new int[G.V()];
        boolean[] vDeleted = new boolean[G.V()];
        int sigma = (int) Double.POSITIVE_INFINITY;

        for (int i = 0; i < G.V(); i++){
            vDegree[i] = G.degree(i);
            if(G.degree(i) < sigma){
                sigma = G.degree(i);
            }
        }
        int nbVertexDeleted = 0;
        int k = sigma;
        while (nbVertexDeleted != G.V()){
            boolean noVertexDeleted = true;
            for (int i = 0; i < G.V(); i++){
                if(!vDeleted[i] && vDegree[i] <= k){
                    vDeleted[i] = true;
                    vDegree[i] = 0;
                    noVertexDeleted = false;
                    nbVertexDeleted += 1;
                    vOrder.addFirst(i);
                    for(int voisin: G.adj(i)){
                        if(!vDeleted[voisin]){
                            vDegree[voisin] -= 1;
                        }
                    }
                }
            }
            if (noVertexDeleted){
                k+=1;
            }
        }
        return k;
    }

    public LinkedList<Integer> getOrder() {
        return vOrder;
    }
}
