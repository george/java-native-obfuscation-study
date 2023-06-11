package dev.george.performance.result;

import dev.george.performance.tests.PerformanceTest;

import java.util.ArrayList;
import java.util.List;

public class PerformanceResults {

    private final List<Long> durations = new ArrayList<>();

    public PerformanceResults(PerformanceTest test) {
        test.preTest();

        for (int i = 0; i < test.getIterations(); i++) {
            durations.add(test.start(i));
        }

        test.cleanup();
    }

    public long getMin() {
        return durations.stream()
                .mapToLong(value -> value)
                .min()
                .getAsLong();
    }

    public long getMax() {
        return durations.stream()
                .mapToLong(value -> value)
                .max()
                .getAsLong();
    }

    public double getAverage() {
        return durations.stream()
                .mapToLong(value -> value)
                .average()
                .getAsDouble();
    }
}
