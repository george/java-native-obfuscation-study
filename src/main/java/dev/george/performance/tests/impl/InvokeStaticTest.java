package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;

public class InvokeStaticTest extends PerformanceTest {

    private static int testInvokeStatic(int iteration) {
        return iteration + 1;
    }

    @Override
    public void handle(int iteration) {
        int result = testInvokeStatic(iteration);
    }

    @Override
    public String getTestName() {
        return "invoke_static";
    }

    @Override
    public int getIterations() {
        return 800;
    }
}
