package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOTest extends PerformanceTest {

    @Override
    public void handle(int iteration) {
        Path path = Paths.get("performance-test.tmp");

        if (path.toFile().exists()) {
            throw new IllegalStateException("File already exists!");
        }

        try {
            path.toFile().createNewFile();
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        path.toFile().delete();
    }

    @Override
    public String getTestName() {
        return "io_test";
    }

    @Override
    public int getIterations() {
        return 50;
    }
}
