package thread.leetcode;

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

public class ProducerConsumerReentrantLock {

    public static void main(String[] args) throws InterruptedException {

        List<String> buffer = new ArrayList<>();

        Lock reentrantLock = new ReentrantLock();
        Condition producerCondition = reentrantLock.newCondition();
        Condition consumerCondition = reentrantLock.newCondition();

        class Consumer implements Callable<String> {

            public String call() throws Exception {
                int count = 0;
                while (count < 10) {
                    reentrantLock.lock();
                    try {
                        while (buffer.isEmpty()) {
                            System.out.println("Consumer - " + count + " " + Thread.currentThread().getName());
                            consumerCondition.await();
                        }
                        buffer.remove(buffer.size() - 1);
                        producerCondition.signalAll();
                    } finally {
                        reentrantLock.unlock();
                    }
                    count++;
                }
                return "Consumer : " + count;
            }
        }

        class Producer implements Callable<String> {

            public String call() throws Exception {
                int count = 0;
                while (count < 10) {
                    reentrantLock.lock();
                    try {
                        while (buffer.size() == 10) {
                            System.out.println("Producer - " + count + " " + Thread.currentThread().getName());
                            producerCondition.await();
                        }
                        buffer.add("val");
                        consumerCondition.signalAll();
                    } finally {
                        reentrantLock.unlock();
                    }
                    count++;
                }
                return "Producer :: " + count;
            }
        }

        List<Consumer> consumers = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            consumers.add(new Consumer());
        }

        List<Producer> producers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            producers.add(new Producer());
        }

        List<Callable<String>> list = new ArrayList<>();
        list.addAll(consumers);
        list.addAll(producers);

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        try {

            List<Future<String>> futures = executorService.invokeAll(list);

            futures.forEach(stringFuture -> {
                try {
                    System.out.println(stringFuture.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });

        } finally {
            executorService.shutdown();
        }

    }
}
