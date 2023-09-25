package com.github.brunolellis;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SampleVirtualThreadsWithSemaphore {

    private static final Semaphore semaphore = new Semaphore(40);

    public static void main(String[] args) {
        var start = Instant.now();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 1; i <= 420; i++) {
                final int taskNumber = i;
                executor.execute(() -> {
                    try {
                        semaphore.acquire();
                        Thread.sleep(1_000);
                        System.out.println("Processing task: " + taskNumber);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        semaphore.release();
                    }
                });
            }
        }

        var delta = ChronoUnit.SECONDS.between(start, Instant.now());

        System.out.printf("Tasks completed in %d seconds!", delta);
        // Tasks completed in 11 seconds!

        // process 'therads' (40) requests at a time!
    }
}