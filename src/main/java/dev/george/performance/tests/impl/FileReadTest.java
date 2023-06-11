package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

public class FileReadTest extends PerformanceTest {

    private final Path tempPath = Paths.get("read");

    @SneakyThrows
    @Override
    public void preTest() {
        byte[] randomBytes = new byte[512];

        for (int i =0 ; i < randomBytes.length; i++) {
            randomBytes[i] = (byte) ThreadLocalRandom.current().nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE);
        }

        tempPath.toFile().createNewFile();
        Files.write(tempPath, randomBytes);
    }

    @SneakyThrows
    @Override
    public void handle(int iteration) {
        Files.readAllBytes(tempPath);
    }

    @Override
    public void cleanup() {
        tempPath.toFile().delete();
    }

    @Override
    public int getIterations() {
        return 128;
    }

    @Override
    public String getTestName() {
        return "file_read";
    }
}
