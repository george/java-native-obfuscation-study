package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class FileWriteTest extends PerformanceTest {

    private final byte[] randomBytes = new byte[512];
    private File directory;

    @Override
    public void preTest() {
        for (int i = 0; i < randomBytes.length; i++) {
            randomBytes[i] = (byte) ThreadLocalRandom.current().nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE);
        }

        directory = new File("temp" + File.separator);
        directory.mkdir();
    }

    @SneakyThrows
    @Override
    public void handle(int iteration) {
        Path path = Paths.get(directory.getPath() + File.separator + iteration);
        Files.write(path, randomBytes);
    }

    public void cleanup() {
        Arrays.stream(Objects.requireNonNull(directory.listFiles())).forEach(File::delete);
        directory.delete();
    }

    @Override
    public int getIterations() {
        return 128;
    }

    @Override
    public String getTestName() {
        return "file_write";
    }
}
