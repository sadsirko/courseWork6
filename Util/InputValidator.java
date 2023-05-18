package Util;

import java.util.HashMap;
import java.util.Map;

public class InputValidator {
    private Map<String, String> regexMap;
    private Map<String, Pair<Integer, Integer>> limitMap;

    public InputValidator() {
        regexMap = new HashMap<>();
        regexMap.put("menuItem", "^[1-4]$");
        regexMap.put("submenuItem", "^[1-2]$");
        regexMap.put("itemNumber", "^[1-9][0-9]{0,9}$");
        regexMap.put("itemNumberAuto", "^[1-9][0-9]{0,9}$");
        regexMap.put("itemNumberManual", "^[1-9][0-9]?$");
        regexMap.put("capacity", "^[1-9][0-9]{0,9}$");
        regexMap.put("capacityAuto", "^[1-9][0-9]{0,9}$");
        regexMap.put("threads", "^[1-9][0-9]?$");

        limitMap = new HashMap<>();
        limitMap.put("menuItem", new Pair<>(1, 20));
        limitMap.put("submenuItem", new Pair<>(1, Integer.MAX_VALUE));
        limitMap.put("itemNumber", new Pair<>(1, Integer.MAX_VALUE));
        limitMap.put("itemNumberManual", new Pair<>(1, 20));
        limitMap.put("itemNumberAuto", new Pair<>(1, Integer.MAX_VALUE));
        limitMap.put("capacity", new Pair<>(1, 32768));
        limitMap.put("threads", new Pair<>(1, 50));
        limitMap.put("values", new Pair<>(1,32768));
        limitMap.put("weights", new Pair<>(1,32768));
        limitMap.put("capacityAuto", new Pair<>(1,Integer.MAX_VALUE));

    }

    private int validateAndReturnValue(String input, String key) throws InvalidInputException, NumberFormatException {
        if (input.equals("")) {
            throw new InvalidInputException("поле не повинно бути порожнім");
        } else {
            if (!input.matches(regexMap.get(key))) {
                throw new InvalidInputException("некоректний формат введення");
            }
            int value = Integer.parseInt(input);
            Pair<Integer, Integer> limits = limitMap.get(key);
            if (value >= limits.getFirst() && value < limits.getSecond()) {
                return value;
            }
            throw new InvalidInputException("некоректне введення");
        }
    }

    public int validateAndReturnMenuItem(String input) throws InvalidInputException, NumberFormatException {
        return validateAndReturnValue(input, "menuItem");
    }

    public int validateAndReturnSubMenuItem(String input) throws InvalidInputException, NumberFormatException {
        return validateAndReturnValue(input, "submenuItem");
    }

    public int validateAndReturnThreadNumber(String input) throws InvalidInputException, NumberFormatException {
        return validateAndReturnValue(input, "threads");
    }

    public int validateAndReturnItemNumberManual(String input) throws InvalidInputException, NumberFormatException {
        return validateAndReturnValue(input, "itemNumberManual");
    }

    public int validateAndReturnItemNumberAuto(String input) throws InvalidInputException, NumberFormatException {
        return validateAndReturnValue(input, "itemNumberAuto");
    }

    public int validateAndReturnCapacity(String input) throws InvalidInputException, NumberFormatException {
        return validateAndReturnValue(input, "capacity");
    }

    public int validateAndReturnCapacityAuto(String input) throws InvalidInputException, NumberFormatException {
        return validateAndReturnValue(input, "capacityAuto");
    }

    public void validateValuesArray(int[] values) throws InvalidInputException {
        Pair<Integer, Integer> limits = limitMap.get("values");
        for (var value : values) {
            if (value < limits.getFirst() || value >= limits.getSecond()) {
                throw new InvalidInputException("некоректне введення");
            }
        }
    }

    public void validateWeightsArray(int[] weights) throws InvalidInputException {
        Pair<Integer,Integer> limits = limitMap.get("weights");
        for (var weight : weights) {
            if (weight < limits.getFirst() || weight >= limits.getSecond()) {
                throw new InvalidInputException("некоректне введення");
            }
        }
    }

}
