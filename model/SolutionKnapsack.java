package model;

import java.util.ArrayList;

public class SolutionKnapsack {
    private final int itemNumber;
    private final int initialCapacity;
    private final int maxWorth;
    private final ArrayList<Integer> itemsUsed;
    private final double timeElapsed;
    private final KnapsackSolver solverUsed;

    public SolutionKnapsack(int itemsNumber, int initialCapacity, int maxWorth, ArrayList<Integer> itemsUsed,
                            double timeElapsed, KnapsackSolver solverUsed) {
        this.itemNumber = itemsNumber;
        this.initialCapacity = initialCapacity;
        this.maxWorth = maxWorth;
        this.itemsUsed = itemsUsed;
        this.timeElapsed = timeElapsed;
        this.solverUsed = solverUsed;
    }

    public static boolean validateSolutions(ArrayList<ArrayList<SolutionKnapsack>> solutions) {
        for (int i = 0; i < solutions.size() - 1; i++) {
            if (!solutions.get(i).equals(solutions.get(i + 1))) {
                return false;
            }
        }
        return true;
    }
    public int getItemNumber() {
        return itemNumber;
    }

    public int getInitialCapacity() {
        return initialCapacity;
    }

    public int getMaxWorth() {
        return maxWorth;
    }

    public ArrayList<Integer> getItemsUsed() {
        return itemsUsed;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public KnapsackSolver getSolverUsed() {
        return solverUsed;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        SolutionKnapsack comparedSolution = (SolutionKnapsack) obj;
        return (itemNumber == comparedSolution.itemNumber && initialCapacity == comparedSolution.initialCapacity
                && maxWorth == comparedSolution.maxWorth && itemsUsed.equals(comparedSolution.itemsUsed));
    }
}
