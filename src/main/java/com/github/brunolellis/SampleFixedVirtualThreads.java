package com.github.brunolellis;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;

public class SampleFixedVirtualThreads {

    public static void main(String[] args) {
        var threads = 40;
        var start = Instant.now();

        // downside is that it is fixed
        try (var executor = Executors.newFixedThreadPool(threads, Thread.ofVirtual().factory())) {
            for (int i = 1; i <= 420; i++) {
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

            var delta = ChronoUnit.SECONDS.between(start, Instant.now());
            System.out.printf("all tasks submitted in %d seconds.%n", delta); // about instantly
        }

        var delta = ChronoUnit.SECONDS.between(start, Instant.now());

        System.out.printf("Tasks completed in %d seconds!", delta);
        // takes only 11 seconds
        // it is limited by 'threads' config
    }
}