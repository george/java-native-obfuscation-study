package dev.george.performance.tests.impl;

import dev.george.performance.tests.PerformanceTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest extends PerformanceTest {

    @Override
    public void handle(int iteration) {
        try {
            Class<?> clazz = Class.forName("dev.george.performance.reflection.ReflectionExample");
            Constructor<?> constructor = clazz.getDeclaredConstructor();

            Field publicStaticField = clazz.getField("EXAMPLE_PUBLIC_STATIC_FIELD");
            Field privateStaticField = clazz.getDeclaredField("EXAMPLE_PRIVATE_STATIC_FIELD");

            Field publicField = clazz.getField("examplePublicField");
            Field privateField = clazz.getDeclaredField("examplePrivateField");

            Method publicStaticMethod = clazz.getMethod("examplePublicStaticMethod");
            Method privateStaticMethod = clazz.getDeclaredMethod("examplePrivateStaticMethod");

            Method publicMethod = clazz.getMethod("examplePublicMethod");
            Method privateMethod = clazz.getDeclaredMethod("examplePrivateMethod");

            constructor.setAccessible(true);

            privateStaticField.setAccessible(true);
            privateStaticMethod.setAccessible(true);

            privateField.setAccessible(true);
            privateMethod.setAccessible(true);

            Object instance = constructor.newInstance();

            int publicStaticFieldResult = publicStaticField.getInt(null);
            int privateStaticFieldResult = privateStaticField.getInt(null);

            int publicStaticMethodResult = (int) publicStaticMethod.invoke(null);
            int privateStaticMethodResult = (int) privateStaticMethod.invoke(null);

            int publicFieldResult = publicField.getInt(instance);
            int privateFieldResult = privateField.getInt(instance);

            int publicMethodResult = (int) publicMethod.invoke(instance);
            int privateMethodResult = (int) privateMethod.invoke(instance);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public int getIterations() {
        return 750;
    }

    @Override
    public String getTestName() {
        return "reflection";
    }
}
