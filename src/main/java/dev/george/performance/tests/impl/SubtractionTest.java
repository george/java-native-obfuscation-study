package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;

public class SubtractionTest extends PerformanceTest {

    @Override
    public void handle(int iteration) {
        int result = iteration - RANDOM_NUMBER;
    }

    @Override
    public String getTestName() {
        return "subtraction";
    }

    @Override
    public int getIterations() {
        return 1000;
    }
}
