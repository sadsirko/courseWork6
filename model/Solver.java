package model;

import model.InputValues;
import model.KnapsackSolver;
import model.SequentialKnapsackSolver;

public class Solver {
    private KnapsackSolver solver;

    public Solver() {
        this.solver = new SequentialKnapsackSolver();
    }

    public Solver(KnapsackSolver solver) {
        this.solver = solver;
    }

    public SolutionKnapsack solve(InputValues inputValues) {
        return solver.solve(inputValues.getItemsNumber(), inputValues.getValues(), inputValues.getWeights(),
                inputValues.getMaxCapacity());
    }

    public SolutionKnapsack solve(int itemsNum, int[] values, int[] weights, int capacity) {
//        System.out.println(capacity);
//        System.out.println(itemsNum);
        return solver.solve(itemsNum, values, weights, capacity);
    }

    public void setSolver(KnapsackSolver newSolver) {
        solver = newSolver;
    }

    public KnapsackSolver getSolver() {
        return solver;
    }
}
