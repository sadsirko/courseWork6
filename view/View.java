package view;

import model.KnapsackSolver;
import model.SolutionKnapsack;

import java.util.ArrayList;

public class View {
    public static final int MENU_CHOOSE_PARALLEL = 1;
    public static final int MENU_CHOOSE_SEQ = 2;
    public static final int MENU_CHOOSE_COMPARE = 3;
    public static final int MENU_EXIT = 4;
    public static final int SUBMENU_MANUAL = 1;
    public static final int SUBMENU_AUTO = 2;

    public final String MENU_CHOOSE_MSG = "Виберіть опцію: ";
    public final String MENU_MSG = ("\n1. Запустити паралельний алгоритм\n" +
            "2. Запустити послідовний алгоритм\n" +
            "3. Запустити обидва та порівняти\n" +
            "4. Вийти\n\n");
    public final String SUBMENU_MSG = ("\n1. Ввести власні значення\n" +
            "2. Згенерувати значення\n\n");
    public final String INVALID_OPTION_MSG = "Неправильна опція\n";
    public final String VALUES_MANUAL_ENTER_MSG = "Введіть кількість предметів (0 < N <= 20): ";
    public final String VALUES_AUTOGENERATED_ENTER_MSG = "Введіть кількість предметів (N > 0): ";
    public final String VALUES_AUTOTEST_ENTER_MSG = "Введіть максимальну кількість предметів для тесту (N > 0): ";
    public final String WEIGHTS_AUTOTEST_ENTER_MSG = "Введіть максимальну вантажопідйомність для тесту (W > 100): ";
    public final String MAX_CAPACITY_ENTER_MSG = "Введіть максимальну вагу, яку ви можете взяти: ";
    public final String THREAD_NUMBER_ENTER_MSG = "Введіть кількість потоків для обробки алгоритму (0 < TN < 50): ";
    public final String WORTH_ENTER_MSG = "Введіть відповідні цінності кожного предмету: ";
    public final String WEIGHTS_ENTER_MSG = "Введіть відповідну вагу предметів: ";
    public final String MAX_VALUE_RESULT_MSG = "Максимально можлива вартість ";
    public final String ITEMS_USED_MSG = "Були використані наступні предмети: ";
    public final String STEP_EXECUTING_MSG = "Виконується обчислення тестового пакету з розміром ";
    public final String EXECUTION_TIME_MSG = "Час виконання ";
    public final String SPEEDUP_MSG = "Прискорення відносно послідовного алгоритму ";
    public final String TESTS_OK_MSG = "Всі тести пройдено коректно!\n";
    public final String TESTS_FAILURE_MSG = "Деякі тести не пройдено!\n";
    public final String SOLVER_USED_MSG = "Для наступних тестів використовується розв'язувач ";
    public final String TRY_AGAIN_MSG = "Вхідні дані некоректні. Спробуйте знову!\n";

    public void printMessage(String message) {
        System.out.print(message);
    }

    public void printTestSolutions(ArrayList<ArrayList<SolutionKnapsack>> testSolutions) {
        for (int i = 0; i < testSolutions.size(); i++) {
            ArrayList<SolutionKnapsack> solutionsSet = testSolutions.get(i);
            System.out.println(SOLVER_USED_MSG + solutionsSet.get(0).getSolverUsed().toString());
            for (int j = 0; j < solutionsSet.size(); j++) {
                System.out.println(STEP_EXECUTING_MSG + "[" + solutionsSet.get(j).getItemNumber() + ", " + solutionsSet.get(j).getInitialCapacity() + "]" + ": ");
                System.out.println(EXECUTION_TIME_MSG + (solutionsSet.get(j).getTimeElapsed()) + " ms");
                if (i > 0) {
                    System.out.println(SPEEDUP_MSG
                            + testSolutions.get(0).get(j).getTimeElapsed() / solutionsSet.get(j).getTimeElapsed());
                }
                System.out.println();
            }
        }
    }

    public void printExecutionStatus(KnapsackSolver solver) {
        System.out.println("Вирішувач " + solver.toString() + " закінчив обчислення!");
    }

    public void printSolution(SolutionKnapsack solution) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n")
                .append(MAX_VALUE_RESULT_MSG)
                .append(solution.getMaxWorth())
                .append("\n")
                .append(ITEMS_USED_MSG);

        ArrayList<Integer> itemsUsed = solution.getItemsUsed();
        for (int i = itemsUsed.size() - 1; i >= 0; i--) {
            sb.append(itemsUsed.get(i)).append(" ");
        }

        sb.append("\n")
                .append(EXECUTION_TIME_MSG)
                .append(solution.getTimeElapsed())
                .append(" ms");

        System.out.println(sb.toString());
    }
}
