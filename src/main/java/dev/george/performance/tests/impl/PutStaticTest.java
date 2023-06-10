package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;

public class PutStaticTest extends PerformanceTest {

    private static int TEST = -1;

    @Override
    public void handle(int iteration) {
        TEST = iteration;
    }

    @Override
    public int getIterations() {
        return 1000;
    }

    @Override
    public String getTestName() {
        return "put_static";
    }
}
