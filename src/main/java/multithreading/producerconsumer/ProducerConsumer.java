package multithreading.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ProducerConsumer<E> {

    private final BlockingQueue<Optional<E>> queue;

    public ProducerConsumer(int numProducerThreads, int numConsumerThreads, int queueCapacity) {
        if (numProducerThreads < 1 || numConsumerThreads < 1 || queueCapacity < 1) {
            throw new IllegalArgumentException();
        }
        queue = new ArrayBlockingQueue<>(queueCapacity);
        final ExecutorService executor = Executors.newFixedThreadPool(numProducerThreads + numConsumerThreads);
        try {
            // Start producer threads
            final List<Future<?>> producerFutures = new ArrayList<>();
            final AtomicInteger numLiveProducers = new AtomicInteger();
            for (int i = 0; i < numProducerThreads; i++) {
                producerFutures.add(executor.submit(() -> {
                    numLiveProducers.incrementAndGet();
                    // Run producer
                    producer();
                    // When last producer finishes, deliver poison pills to consumers
                    if (numLiveProducers.decrementAndGet() == 0) {
                        for (int j = 0; j < numConsumerThreads; j++) {
                            queue.put(Optional.empty());
                        }
                    }
                    return null;
                }));
            }
            // Start consumer threads
            final List<Future<?>> consumerFutures = new ArrayList<>();
            for (int i = 0; i < numConsumerThreads; i++) {
                consumerFutures.add(executor.submit(() -> {
                    // Run Consumer
                    consumer();
                    return null;
                }));
            }
            // Wait for all producers to complete
            completionBarrier(producerFutures, false);
            // Shut down any consumers that are still running after producers complete
            completionBarrier(consumerFutures, false);
        } finally {
            executor.shutdownNow();
        }
    }

    private static void completionBarrier(List<Future<?>> futures, boolean cancel) {
        for (Future<?> future : futures) {
            try {
                if (cancel) {
                    future.cancel(true);
                }
                future.get();
            } catch (CancellationException | InterruptedException e) {
                // Ignore
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void produce(E val) {
        try {
            queue.put(Optional.of(val));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected Optional<E> consume() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Producer loop. Call {@link #produce(E)} for each element.
     */
    public abstract void producer();

    /**
     * Consumer thread. Call {@link #consume()} to get each successive element,
     * until an empty {@link Optional} is returned.
     */
    public abstract void consumer();
}

