package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;

import java.io.File;

public class DirectoryTest extends PerformanceTest {

    @Override
    public void handle(int iteration) {
        File directory = new File(iteration + File.separator);

        directory.mkdir();

        directory.delete();
    }

    @Override
    public int getIterations() {
        return 60;
    }

    @Override
    public String getTestName() {
        return "directory";
    }
}
