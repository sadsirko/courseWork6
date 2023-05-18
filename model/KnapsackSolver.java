package model;

public abstract class KnapsackSolver {
    abstract public Solution solve(int itemsNum, int values[], int weights[], int capacity);
}
