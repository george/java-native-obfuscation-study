package dev.george.performance.report;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ParsedReport {

    private final Map<String, Double> averages = new HashMap<>();

    private final boolean isNative;

    private final double memoryRatio;

    public ParsedReport(Gson gson, Path path) {
        JsonObject object = null;

        try {
            object = gson.fromJson(new String(Files.readAllBytes(path)), JsonObject.class);
        } catch (IOException exc) {
            exc.printStackTrace();

            throw new RuntimeException("An unexpected error occurred while parsing report " + path.toFile().getAbsolutePath() + "!");
        }

        object.get("results").getAsJsonArray().forEach(result -> {
            JsonObject resultObject = result.getAsJsonObject();

            averages.put(resultObject.get("name").getAsString(), resultObject.get("average").getAsDouble());
        });
        
        this.memoryRatio = object.get("memory_allocated").getAsDouble() /
                object.get("memory_in_use").getAsDouble();

        this.isNative = path.toFile().getName().startsWith("report-native");
    }
}
