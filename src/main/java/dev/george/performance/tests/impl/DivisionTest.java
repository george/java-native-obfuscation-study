package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;

public class DivisionTest extends PerformanceTest {

    @Override
    public void handle(int iteration) {
        int result = (iteration + 1000) / RANDOM_NUMBER;
    }

    @Override
    public String getTestName() {
        return "division";
    }

    @Override
    public int getIterations() {
        return 1000;
    }
}
