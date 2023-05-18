package model;

public class Solver {
    private KnapsackSolver solver;

    public Solver() {
        this.solver = new SerialDPKnapsackSolver();
    }

    public Solver(KnapsackSolver solver) {
        this.solver = solver;
    }

    public Solution solve(Parameters inputValues) {
        return solver.solve(inputValues.getItemsNumber(), inputValues.getValues(), inputValues.getWeights(),
                inputValues.getMaxCapacity());
    }

    public Solution solve(int itemsNum, int[] values, int[] weights, int capacity) {
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
