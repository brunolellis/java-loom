package com.github.brunolellis;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FixedVirtualThreadExecutor extends ThreadPoolExecutor {

    public FixedVirtualThreadExecutor(int maxPoolSize) {
        super(maxPoolSize,
                maxPoolSize,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                Thread.ofVirtual().factory()
        );
    }

}
