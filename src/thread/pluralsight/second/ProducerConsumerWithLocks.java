package thread.pluralsight.second;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLocks {

    public static void main(String[] args) throws InterruptedException {

        List<Integer> buffer = new ArrayList<>();

        Lock lock = new ReentrantLock(); // only one lock exists
        Condition consumerCondition = lock.newCondition();
        Condition producerCondition = lock.newCondition();

        class Consumer implements Callable<String> {

            @Override
            public String call() throws InterruptedException {
                int count = 0;
                for (; count < 5; count++) {
                    try {
                        lock.lock();
                        while (buffer.size() == 0) { //spurious wakeup
                            consumerCondition.await(); // this thread release the lock and  goes to wait stage
                        }
                        buffer.remove(buffer.size() - 1);
                        producerCondition.signalAll(); // this thread doesn't release the lock unlike await
                    } finally {
                        lock.unlock();
                    }
                }
                return "Consumed " + (count - 1);
            }
        }

        class Producer implements Callable<String> {

            @Override
            public String call() throws InterruptedException {
                int count = 0;
                for (; count < 5; count++) {
                    try {
                        lock.lock();
                        //int i = 10 / 0;
                        while (buffer.size() == 3) {
                            producerCondition.await();
                        }
                        buffer.add(1);
                        consumerCondition.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
                return "Produced " + (count - 1);
            }
        }

        List<Producer> producers = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            producers.add(new Producer());
        }

        List<Consumer> consumers = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            consumers.add(new Consumer());
        }

        System.out.println("Producers and Consumers launched");

        List<Callable<String>> producersAndConsumers = new ArrayList<>();
        producersAndConsumers.addAll(producers);
        producersAndConsumers.addAll(consumers);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        try {
            List<Future<String>> futures = executorService.invokeAll(producersAndConsumers);

            futures.forEach(future -> {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            });

        } finally {
            executorService.shutdown();
            System.out.println("Executor service shut down");
        }
    }
}
