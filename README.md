# Java Loom and ExecutorService

Trying to handle how many virtual threads may run in parallel. Think about throttle and how to avoid overloading external systems.

## 1. `SampleFixedThreadPool`

that is the way we are used to: create a fixed thread pool (or a cached thread pool) and submit jobs

## 2. `SampleUnboundedVirtualThreads`

it is that easy: just create an ExecutorService with `Executors.newVirtualThreadPerTaskExecutor()` and submit your jobs.

but you may process thousands or millions of processes concurrently and end up overloading an external system.

## 3. `SampleFixedVirtualThreads`

that's the same implementation as _1_ but using Project's Loom Virtual Threads implementation with fixed number of virtual threads.

## 4. `SampleCustomVirtualThreads`

tried something different customizing `ThreadPoolExecutor` but at the end of the day, it is the same as the _3_.  