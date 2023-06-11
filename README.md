# Java Native Obfuscation Performance Test

### Introduction

Protection of Java applications can be enhanced in a few ways. Authors of programs
can choose to use an obfuscator such as Zelix KlassMaster, Skidfuscator,
and countless other ones, but oftentimes, this isn't enough. There's a limit
as to how much you can protect an application in Java alone.

This is where Java native obfuscators come into play. These tools are able to convert Java code
into C++ code by converting the Java bytecode into native JNI calls. This C++
can then be compiled into native libraries, which are then securely loaded
into a JAR file. After this, the bytecode is cleared from the method bodies
of the methods being replaced with native function calls.

As a result of this, reversing the JAR file by decompiling it is useless,
as methods converted to native JNI calls won't have any bytecode to view.

While it has its benefits, converting bytecode to native JNI calls
is a very heavy process on the application calling the native functions.
This program calculates the average expense of Java operations, to determine
exactly how significant these native operations are on Java programs.

### Process

The process behind measuring the performance impact isn't difficult. This
program measures the following operations and actions:

- Addition
- Basic array operations
- Directory creation
- Division
- Exceptions
- File Reading
- File Writing
- GETSTATIC operations
- INVOKEDYNAMIC operations
- INVOKESPECIAL operations
- INVOKESTATIC operations
- IO operations
- Multiplication
- PUTSTATIC operations
- Random Number Generation
- Reflective operations
- Subtraction

The first step in measuring the performance differential is to establish
a base speed for these operations, in nanoseconds, anywhere between 50
and 3,000 times.

Next, convert the bytecode to JNI calls (using one of the tools in the
references section), and perform the same operations for the same amount of iterations.

Finally, simply compare the numbers.

### Results

Not surprisingly, bytecode level operations were exponentially faster
an overwhelming majority of the time, though strangely, certain IO operations
over twice as fast (128%) natively.

The results are as follows:

| Operation                | Speed Difference | Call Type |
|--------------------------|------------------|-----------|
| Addition                 | 1,606.035%       | Standard  |
| Array Operations         | 889.806%         | Standard  |
| Directory Operations     | 128.973%         | Native    |
| Division                 | 1,917.864%       | Standard  |
| Exceptions               | 1,094.185%       | Standard  |
| File Read                | 113.393%         | Standard  |
| File Write               | 103.501%         | Standard  |
| GETSTATIC Operations     | 1,213.116%       | Standard  |
| INVOKEDYNAMIC Operations | 14,619.96%       | Standard  |
| INVOKESPECIAL Operations | 297.307%         | Standard  |
| INVOKESTATIC Operations  | 6,914.189%       | Standard  |
| IO Operations            | 128.341%         | Native    |
| Multiplication           | 1,155.77%        | Standard  |
| PUTSTATIC Operations     | 1,229.117%       | Standard  |
| Random Number Generation | 212.254%         | Standard  |
| Reflection               | 118.309%         | Standard  |
| Subtraction              | 1,209.519%       | Standard  |

### References / Tools

- https://github.com/radioegor146/native-obfuscator - Opensource native obfuscator
- https://jnic.dev - Commercial native obfuscator made by Konsolas
