package model;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelKnapsackSolver extends KnapsackSolver {
    private int threadCount;

    public ParallelKnapsackSolver() {
        this.threadCount = Runtime.getRuntime().availableProcessors();
    }

    public ParallelKnapsackSolver(int threadCount) {
        this.threadCount = Runtime.getRuntime().availableProcessors();
        if(threadCount < this.threadCount)
            this.threadCount = threadCount;
    }

    public SolutionKnapsack solve(int num, int values[], int weights[], int volume) {
//        створюється пул потоків
        ExecutorService pool = Executors.newFixedThreadPool(threadCount);

        long startTime = System.nanoTime();

        int slSize = (volume + 1) / threadCount;

        int dpTable[][] = new int[num + 1][volume + 1];
// заходимо на 1 предмет
        for (int i = 1; i <= num; i++) {

            CountDownLatch latch = new CountDownLatch(threadCount);
//            Для кожного потоку в пулі потоків створюється завдання, яке обчислює оптимальне рішення для частини масиву
            for (int slice = 0; slice < threadCount; slice++) {
                int currentItem = i;
                int leftBound = slice * slSize;
                int rightBound = slice == threadCount - 1 ? volume : leftBound + slSize - 1;
//Для кожного потоку в пулі потоків створюється завдання, яке обчислює оптимальне рішення для частини масиву dpTable[][].
                pool.submit(() -> {
                    for (int w = leftBound; w <= rightBound; w++) {
                        if (weights[currentItem - 1] > w) {
                            dpTable[currentItem][w] = dpTable[currentItem - 1][w];
                        } else {
                            dpTable[currentItem][w] = Math.max(dpTable[currentItem - 1][w],
                                    values[currentItem - 1] + dpTable[currentItem - 1][w - weights[currentItem - 1]]);
                        }
                    }
//лічильник CountDownLatch зменшується на одиницю
                    latch.countDown();
                });
            }
            try {
//Головний потік чекає, поки всі потоки не завершать виконання своїх завдань
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//Після завершення обробки всіх предметів пул потоків закривається,
// а головний потік чекає, поки всі потоки не завершать виконання своїх завдань
        pool.shutdown();

        ArrayList<Integer> itemsUsed = new ArrayList<Integer>();
        int k = num;
        int w = volume;

        while (k > 0) {
            if (dpTable[k][w] != dpTable[k - 1][w]) {
                itemsUsed.add(k);
                w -= weights[k - 1];
            }
            k--;
        }

        long duration = (System.nanoTime() - startTime) / 1000000;

        return new SolutionKnapsack(num, volume, dpTable[num][volume], itemsUsed, duration,
                this);
    }
}
