package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;

public class GetStaticTest extends PerformanceTest {

    private static final int TEST = 5;

    @Override
    public void handle(int iteration) {
        int a = TEST;
    }

    @Override
    public int getIterations() {
        return 1000;
    }

    @Override
    public String getTestName() {
        return "get_static";
    }
}
