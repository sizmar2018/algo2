import edu.princeton.cs.algs4.Graph;

public class KCoresAlgorithm {

    public int[] getCores(Graph G){

        int[] vCores = new int[G.V()];
        int[] vDegree = new int[G.V()];
        boolean[] vDeleted = new boolean[G.V()];
        int k = (int) Double.POSITIVE_INFINITY;

        for (int i = 0; i < G.V(); i++){
            vDegree[i] = G.degree(i);
            if(G.degree(i) < k){
                k = G.degree(i);
            }
        }

        int nbVertexDeleted = 0;
        while (nbVertexDeleted != G.V()){
            boolean noVertexDeleted = true;
            for (int i = 0; i < G.V(); i++){
                if(!vDeleted[i] && vDegree[i] <= k){
                    vDeleted[i] = true;
                    vDegree[i] = 0;
                    noVertexDeleted = false;
                    nbVertexDeleted += 1;
                    vCores[i] = k;
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
        return vCores;
    }
}
