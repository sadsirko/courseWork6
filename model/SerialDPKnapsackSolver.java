package model;

import java.util.ArrayList;

public class SerialDPKnapsackSolver extends KnapsackSolver {
    public Solution solve(int num, int values[], int weights[], int volumes) {

        long startTime = System.nanoTime();

        int i, w;
        int K[][] = new int[num + 1][volumes + 1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= num; i++) {
            for (w = 0; w <= volumes; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (weights[i - 1] <= w)
                    K[i][w]
                            = Math.max(values[i - 1]
                                    + K[i - 1][w - weights[i - 1]],
                            K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        ArrayList<Integer> itemsUsed = new ArrayList<Integer>();

        int n_counter = num;
        int w_counter = volumes;

        while (n_counter > 0) {
            if (K[n_counter][w_counter] != K[n_counter - 1][w_counter]) {
                itemsUsed.add(n_counter);
                w_counter -= weights[n_counter - 1];
            }
            n_counter--;
        }

        long dur = (System.nanoTime() - startTime)/ 1000000;
        return new Solution(num, volumes, K[num][volumes], itemsUsed, dur ,
                this);
    }
}
