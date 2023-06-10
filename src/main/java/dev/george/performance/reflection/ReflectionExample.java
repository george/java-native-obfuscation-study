package dev.george.performance.reflection;

public class ReflectionExample {

    public static final int EXAMPLE_PUBLIC_STATIC_FIELD = 0;
    private static final int EXAMPLE_PRIVATE_STATIC_FIELD = 1;

    public int examplePublicField = 0;
    private int examplePrivateField = 1;

    private ReflectionExample() {}

    public int examplePublicMethod() {
        return 0;
    }

    private int examplePrivateMethod() {
        return 1;
    }

    public static int examplePublicStaticMethod() {
        return 0;
    }

    private static int examplePrivateStaticMethod() {
        return 1;
    }
}
