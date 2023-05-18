package tetsdata;

import model.InputValues;

import java.util.ArrayList;
import java.util.Random;

public class DataGenerator {
    private final Random rnd;
    private final int MIN_VALUE  = 100;
    private final int MAX_VALUE = 10000; // 32768
    private final int MIN_WEIGHT  = 100;
    private final int MAX_WEIGHT = 10000;
    private final int MIN_CAPACITY  = 9900000;
    private final int MAX_CAPACITY = 10000000; // 32768
    private final int NUMBER_OF_SETS = 10;

    public DataGenerator() {
        rnd = new Random();
    }

    public InputValues generateInputValues(int itemCount, int capacity) {
        return new InputValues(itemCount, capacity, generateArrayInBorders(itemCount, MIN_VALUE , MAX_VALUE),
                generateArrayInBorders(itemCount, MIN_WEIGHT , MAX_WEIGHT));
    }

    public InputValues generateInputValues(int itemCount) {
        return new InputValues(itemCount, rnd.nextInt(MIN_CAPACITY , MAX_CAPACITY),
                generateArrayInBorders(itemCount, MIN_VALUE , MAX_VALUE),
                generateArrayInBorders(itemCount, MIN_WEIGHT , MAX_WEIGHT));
    }

    private int[] generateArrayInBorders(int itemCount, int lowerBound, int upperBound) {
        int arr[] = new int[itemCount];
        for (int i = 0; i < itemCount; i++) {
            arr[i] = rnd.nextInt(lowerBound, upperBound);
        }
        return arr;
    }

    public ArrayList<InputValues> generateTestSets(int maxItemNumber, int maxCapacity) {
        ArrayList<InputValues> testSets = new ArrayList<InputValues>(NUMBER_OF_SETS);
        int setSizeItems = maxItemNumber / NUMBER_OF_SETS;
        int setSizeCapacity = maxCapacity / NUMBER_OF_SETS;
        for (int i = 0; i < NUMBER_OF_SETS; i++) {
            int currentItemNumber = (i == NUMBER_OF_SETS - 1) ? maxItemNumber : (i + 1) * setSizeItems;
            int currentCapacity = (i == NUMBER_OF_SETS - 1) ? maxCapacity : (i + 1) * setSizeCapacity;
            int reserveItemNumber = maxItemNumber;
            int reserveCapacity =  maxCapacity;
            testSets.add(generateInputValues(currentItemNumber, currentCapacity));
        }
        return testSets;
    }
}
