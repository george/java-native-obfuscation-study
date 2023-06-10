package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;

public class InvokeSpecialTest extends PerformanceTest {

    private static class InvokeSpecialTestExample {

    }

    @Override
    public String getTestName() {
        return "invoke_special";
    }

    @Override
    public void handle(int iteration) {
        new InvokeSpecialTestExample();
    }

    @Override
    public int getIterations() {
        return 2500;
    }
}
