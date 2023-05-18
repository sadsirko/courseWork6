package model;

import tetsdata.DataGen;

import java.util.ArrayList;

public class Model {
    private final Solver solver;
    private final DataGen dataGenerator;
    private ArrayList<Parameters> testSet;
    private ArrayList<ArrayList<Solution>> testSolutions;

    public Model() {
        solver = new Solver();
        dataGenerator = new DataGen();
        testSolutions = new ArrayList<ArrayList<Solution>>();
    }
    public ArrayList<Solution> runTestSets() {
        return runCertainTestSet(testSet);
    }

    public Parameters generateInputValues(int itemsNumber) {
        return dataGenerator.generateInputValues(itemsNumber);
    }

    public ArrayList<Solution> runTestSets(ArrayList<Parameters> testSets) {
        return runCertainTestSet(testSets);
    }


    public void setNewSolver(KnapsackSolver knapsackSolver) {
        solver.setSolver(knapsackSolver);
    }

    public ArrayList<Parameters> generateTestSets(int maxItemNumber, int maxCapacity) {
        testSolutions.clear();
        testSet = dataGenerator.generateTestSets(maxItemNumber, maxCapacity);
        return testSet;
    }

    public boolean checkIfEverythingIsSimilar(ArrayList<ArrayList<Solution>> solutions) {
        return Solution.validateSolutions(solutions);
    }

    public void addTestSolutions(ArrayList<Solution> solutions) {
        testSolutions.add(solutions);
    }

    private ArrayList<Solution> runCertainTestSet(ArrayList<Parameters> testSets) {
        ArrayList<Solution> result = new ArrayList<Solution>(testSets.size());
        for (var inputValues : testSets) {
            result.add(solver.solve(inputValues));
        }
        return result;
    }

    public Solution solve(int itemsNum, int values[], int weights[], int capacity) {
        return solver.solve(itemsNum, values, weights, capacity);
    }

    public ArrayList<ArrayList<Solution>> getTestSolutions() {
        return testSolutions;
    }
}
