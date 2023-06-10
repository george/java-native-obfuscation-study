package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;

public class InvokeDynamicTest extends PerformanceTest {

    /*
     * Since Lambda functions are implemented with
     * invokedynamics, we're able to create a simple
     * Runnable and execute it in order to measure its
     * performance impact.
     */
    @Override
    public void handle(int iteration) {
        Runnable runnable = () -> {
            int a = 5 + 10;
        };

        runnable.run();
    }

    @Override
    public String getTestName() {
        return "invoke_dynamic";
    }

    @Override
    public int getIterations() {
        return 3000;
    }
}
