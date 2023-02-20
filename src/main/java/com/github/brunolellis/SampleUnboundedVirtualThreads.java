package com.github.brunolellis;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;

public class SampleUnboundedVirtualThreads {

    public static void main(String[] args) {
        var start = Instant.now();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 1; i <= 42; i++) {
                final int taskNumber = i;
                executor.execute(() -> {
                    System.out.println("Processing task: " + taskNumber);

                    try {
                        Thread.sleep(1_000);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }

        var delta = ChronoUnit.SECONDS.between(start, Instant.now());

        System.out.printf("Tasks completed in %d seconds!", delta);
        // takes only 1 second!
        // but may overload an external system...
    }
}