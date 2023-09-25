package com.github.brunolellis;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;

public class SampleFixedThreadPool {

    public static void main(String[] args) {
        var threads = 10;
        var start = Instant.now();

        try (var executor = Executors.newFixedThreadPool(threads)) {
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
        // Tasks completed in 5 seconds!
        // takes 5 seconds: 42 / 10 + 1
    }
}