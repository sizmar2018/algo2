import edu.princeton.cs.algs4.Graph;

import java.util.Arrays;

public class TestDegeneracy {
    public static void main(String[] args) {
        DegeneracyAlgorithm degeneracyAlgorithm = new DegeneracyAlgorithm();
        KCoresAlgorithm kCoresAlgorithm = new KCoresAlgorithm();

        Graph g1 = new Graph(9);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(1, 5);
        g1.addEdge(2, 3);
        g1.addEdge(2, 4);
        g1.addEdge(2, 5);
        g1.addEdge(2, 6);
        g1.addEdge(3, 4);
        g1.addEdge(3, 6);
        g1.addEdge(3, 7);
        g1.addEdge(4, 6);
        g1.addEdge(4, 7);
        g1.addEdge(5, 6);
        g1.addEdge(5, 8);
        g1.addEdge(6, 7);
        g1.addEdge(6, 8);

        int k = degeneracyAlgorithm.getKDegenerate(g1);
        int[] profondeur = kCoresAlgorithm.getCores(g1);
        System.out.println("Graph 1 is " + k+ "-degenerate");
        System.out.println(Arrays.toString(profondeur)+"\n");

        Graph g2 = new Graph(13);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(0, 3);
        g2.addEdge(1, 4);
        g2.addEdge(1, 5);
        g2.addEdge(1, 6);
        g2.addEdge(2, 7);
        g2.addEdge(2, 8);
        g2.addEdge(2, 9);
        g2.addEdge(3, 10);
        g2.addEdge(3, 11);
        g2.addEdge(3, 12);

        k = degeneracyAlgorithm.getKDegenerate(g2);
        System.out.println("Graph 2 is " + k+ "-degenerate");
        profondeur = kCoresAlgorithm.getCores(g2);
        System.out.println(Arrays.toString(profondeur)+"\n");

        Graph g3 = new Graph(29);
        g3.addEdge(0,1);
        g3.addEdge(1,4);
        g3.addEdge(4,6);
        g3.addEdge(2,3);
        g3.addEdge(2,6);
        g3.addEdge(6,7);
        g3.addEdge(4,5);
        g3.addEdge(5,7);
        g3.addEdge(5,8);
        g3.addEdge(7,9);
        g3.addEdge(8,9);
        g3.addEdge(8,11);
        g3.addEdge(11,12);
        g3.addEdge(9,12);
        g3.addEdge(8,13);
        g3.addEdge(11,13);
        g3.addEdge(16,9);
        g3.addEdge(14,12);
        g3.addEdge(16,14);
        g3.addEdge(16,15);
        g3.addEdge(14,15);
        g3.addEdge(16,17);
        g3.addEdge(15,18);
        g3.addEdge(10,8);
        g3.addEdge(10,11);
        g3.addEdge(10,12);
        g3.addEdge(10,9);
        g3.addEdge(11,22);
        g3.addEdge(12,19);
        g3.addEdge(19,20);
        g3.addEdge(19,21);
        g3.addEdge(21,22);
        g3.addEdge(20,22);
        g3.addEdge(21,27);
        g3.addEdge(27,26);
        g3.addEdge(27,28);
        g3.addEdge(11,25);
        g3.addEdge(25,24);
        g3.addEdge(24,23);
        k = degeneracyAlgorithm.getKDegenerate(g3);
        System.out.println("Graph 3 is " + k+ "-degenerate");
        profondeur = kCoresAlgorithm.getCores(g3);
        System.out.println(Arrays.toString(profondeur));
    }
}
