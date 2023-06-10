package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;

public class ArrayTest extends PerformanceTest {

    @Override
    public void handle(int iteration) {
        int[] array = new int[iteration + 1];

        for (int i = 0; i <  iteration; i++) {
            array[i] = RANDOM_NUMBER;
        }
    }

    @Override
    public String getTestName() {
        return "array";
    }

    @Override
    public int getIterations() {
        return 2000;
    }
}
