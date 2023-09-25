package com.github.brunolellis;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class SampleCustomVirtualThreads {

    public static void main(String[] args) {
        var threads = 40;
        var start = Instant.now();

        try (var executor = new FixedVirtualThreadExecutor(threads)) {
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
            System.out.printf("all tasks submitted in %d seconds.%n", delta);
        }

        var delta = ChronoUnit.SECONDS.between(start, Instant.now());

        System.out.printf("Tasks completed in %d seconds!", delta);
        // Tasks completed in 11 seconds!

        // it is limited by 'threads' config
    }

}
