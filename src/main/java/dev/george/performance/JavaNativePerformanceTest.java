package dev.george.performance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dev.george.performance.report.ParsedReport;
import dev.george.performance.report.PerformanceReport;
import dev.george.performance.result.PerformanceResults;
import dev.george.performance.tests.PerformanceTest;
import dev.george.performance.tests.impl.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Function;

public class JavaNativePerformanceTest {

    private final List<PerformanceTest> performanceTests = Arrays.asList(
            new AdditionTest(),
            new ArrayTest(),
            new DirectoryTest(),
            new DivisionTest(),
            new ExceptionTest(),
            new GetStaticTest(),
            new InvokeDynamicTest(),
            new InvokeSpecialTest(),
            new InvokeStaticTest(),
            new IOTest(),
            new MultiplicationTest(),
            new PutStaticTest(),
            new RandomTest(),
            new ReflectionTest(),
            new SubtractionTest()
    );

    public void start() {
        Path outputDirectory = Paths.get("reports" + File.separator);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        if (!outputDirectory.toFile().isDirectory()) {
            outputDirectory.toFile().mkdir();
        }

        File[] files = Objects.requireNonNull(outputDirectory.toFile().listFiles());

        if (files.length >= 2) {
            if (Arrays.stream(files).anyMatch(file -> file.getName().startsWith("report-native"))) {
                List<ParsedReport> reports = Arrays.asList(
                        new ParsedReport(gson, Paths.get(files[0].getAbsolutePath())),
                        new ParsedReport(gson, Paths.get(files[1].getAbsolutePath()))
                );

                performanceTests.forEach(test -> {
                    Comparator<ParsedReport> averageComparator = Comparator.comparingDouble(
                            parsedReport -> parsedReport.getAverages().get(test.getTestName()));

                    Optional<ParsedReport> slowerOptional = reports.stream().max(averageComparator);
                    Optional<ParsedReport> fasterOptional = reports.stream().min(averageComparator);

                    Function<ParsedReport, Double> reportToAverage = (report) -> report.getAverages().get(test.getTestName());

                    if (!slowerOptional.isPresent() || !fasterOptional.isPresent()) {
                        throw new RuntimeException("An unexpected error occurred while attempting to compare test" +
                                "results for " + test.getTestName() + "!");
                    }

                    double slower = reportToAverage.apply(slowerOptional.get());
                    double faster = reportToAverage.apply(fasterOptional.get());

                    double ratio = slower / faster;

                    System.out.printf("%s is faster by %s%% for %s!%n",
                            fasterOptional.get().isNative() ? "Native" : "Non native",
                            NumberFormat.getInstance().format((ratio * 100)),
                            test.getTestName()
                    );
                });
            }

            return;

        }
        System.out.println("Beginning performance tests...");

        PerformanceReport report = new PerformanceReport();

        performanceTests.forEach(test -> {
            long startTime = System.currentTimeMillis();

            PerformanceResults results = new PerformanceResults(test);
            long timeElapsed = System.currentTimeMillis() - startTime;

            System.out.println("Successfully completed " + test.getTestName() + " in " + timeElapsed + " ms!");

            report.add(test, results);
        });

        Path outputFile = Paths.get(outputDirectory.toFile() + File.separator + "report-" + System.currentTimeMillis() + ".json");

        try {
            Files.write(outputFile, gson.toJson(report.getResults()).getBytes());
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JavaNativePerformanceTest().start();
    }
}
