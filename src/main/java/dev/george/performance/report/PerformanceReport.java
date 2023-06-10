package dev.george.performance.report;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.george.performance.result.PerformanceResults;
import dev.george.performance.tests.PerformanceTest;

import java.util.HashMap;
import java.util.Map;

public class PerformanceReport {

    private final Map<String, PerformanceResults> results = new HashMap<>();

    public void add(PerformanceTest test, PerformanceResults result) {
        results.put(test.getTestName(), result);
    }

    public JsonObject getResults() {
        JsonObject object = new JsonObject();

        object.addProperty("memory_allocated", Runtime.getRuntime().maxMemory());
        object.addProperty("memory_in_use", Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        JsonArray resultsArray = new JsonArray();

        results.forEach((name, performanceResults) -> {
            JsonObject resultsObject = new JsonObject();

            resultsObject.addProperty("name", name);

            resultsObject.addProperty("min", performanceResults.getMin());
            resultsObject.addProperty("max", performanceResults.getMax());
            resultsObject.addProperty("average", performanceResults.getAverage());

            resultsArray.add(resultsObject);
        });

        object.add("results", resultsArray);

        return object;
    }
}
