package model;

import java.util.ArrayList;

public class SequentialKnapsackSolver extends KnapsackSolver {
    public SolutionKnapsack solve(int n, int val[], int wt[], int W) {

        long startTime = System.nanoTime();

        int i, w;
        int K[][] = new int[n + 1][W + 1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w]
                            = Math.max(val[i - 1]
                                    + K[i - 1][w - wt[i - 1]],
                            K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        ArrayList<Integer> itemsUsed = new ArrayList<Integer>();

        int n_counter = n;
        int w_counter = W;

        while (n_counter > 0) {
            if (K[n_counter][w_counter] != K[n_counter - 1][w_counter]) {
                itemsUsed.add(n_counter);
                w_counter -= wt[n_counter - 1];
            }
            n_counter--;
        }

        long dur = (System.nanoTime() - startTime)/ 1000000;
        return new SolutionKnapsack(n, W, K[n][W], itemsUsed, dur ,
                this);
    }
}
