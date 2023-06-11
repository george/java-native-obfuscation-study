package dev.george.performance.tests;

public abstract class PerformanceTest {

    protected static final int RANDOM_NUMBER = 5;

    public abstract void handle(int iteration);

    public abstract int getIterations();

    public abstract String getTestName();

    public void preTest() {}

    public void cleanup() {}

    public long start(int iteration) {
        long nanoTime = System.nanoTime();

        handle(iteration);

        return System.nanoTime() - nanoTime;
    }
}
