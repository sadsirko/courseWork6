package model;

import tetsdata.DataGenerator;

import java.util.ArrayList;

public class Model {
    private final Solver solver;
    private final DataGenerator dataGenerator;
    private ArrayList<InputValues> testSet;
    private ArrayList<ArrayList<SolutionKnapsack>> testSolutions;

    public Model() {
        solver = new Solver();
        dataGenerator = new DataGenerator();
        testSolutions = new ArrayList<ArrayList<SolutionKnapsack>>();
    }

    public ArrayList<SolutionKnapsack> runTestSets(ArrayList<InputValues> testSets) {
        return runCertainTestSet(testSets);
    }

    public ArrayList<SolutionKnapsack> runTestSets() {
        return runCertainTestSet(testSet);
    }

    public InputValues generateInputValues(int itemsNumber) {
        return dataGenerator.generateInputValues(itemsNumber);
    }

    public void setNewSolver(KnapsackSolver knapsackSolver) {
        solver.setSolver(knapsackSolver);
    }

    public ArrayList<InputValues> generateTestSets(int maxItemNumber, int maxCapacity) {
        testSolutions.clear();
        testSet = dataGenerator.generateTestSets(maxItemNumber, maxCapacity);
        return testSet;
    }

    public boolean checkIfEverythingIsSimilar(ArrayList<ArrayList<SolutionKnapsack>> solutions) {
        return SolutionKnapsack.validateSolutions(solutions);
    }

    public void addTestSolutions(ArrayList<SolutionKnapsack> solutions) {
        testSolutions.add(solutions);
    }

    private ArrayList<SolutionKnapsack> runCertainTestSet(ArrayList<InputValues> testSets) {
        ArrayList<SolutionKnapsack> result = new ArrayList<SolutionKnapsack>(testSets.size());
        for (var inputValues : testSets) {
            result.add(solver.solve(inputValues));
        }
        return result;
    }

    public SolutionKnapsack solve(int itemsNum, int values[], int weights[], int capacity) {
        return solver.solve(itemsNum, values, weights, capacity);
    }

    public ArrayList<ArrayList<SolutionKnapsack>> getTestSolutions() {
        return testSolutions;
    }
}
