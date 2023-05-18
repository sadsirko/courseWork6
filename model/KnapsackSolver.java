package model;

public abstract class KnapsackSolver {
    abstract public SolutionKnapsack solve(int itemsNum, int values[], int weights[], int capacity);
}
