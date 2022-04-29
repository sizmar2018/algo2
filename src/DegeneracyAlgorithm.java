import edu.princeton.cs.algs4.Graph;

import java.util.LinkedList;

public class DegeneracyAlgorithm {

    private final Graph G;
    public LinkedList<Integer> vOrder;

    public DegeneracyAlgorithm(Graph graph) {
        this.G = graph;
    }

    public int getKDegenerate(){
        int[] vDegree = new int[G.V()];
        this.vOrder = new LinkedList<>();
        boolean[] vDeleted = new boolean[G.V()];
        int nbVertexDeleted = 0;

        int k = (int) Double.POSITIVE_INFINITY;
        for (int i = 0; i < G.V(); i++){
            vDegree[i] = G.degree(i);
            if(G.degree(i) < k){
                k = G.degree(i);
            }
        }

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

    public static void main(String[] args) {

    }
}
