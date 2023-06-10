package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;

public class RandomTest extends PerformanceTest {

    @Override
    public void handle(int iteration) {
        double number = Math.random();
    }

    @Override
    public int getIterations() {
        return 1800;
    }

    @Override
    public String getTestName() {
        return "random";
    }
}
