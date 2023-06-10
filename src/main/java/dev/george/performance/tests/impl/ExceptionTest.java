package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;

import java.io.IOException;

public class ExceptionTest extends PerformanceTest {

    @Override
    public void handle(int iteration) {
        try {
            throw new IOException();
        } catch (IOException exc) {

        }
    }

    @Override
    public String getTestName() {
        return "exception";
    }

    @Override
    public int getIterations() {
        return 1500;
    }
}
