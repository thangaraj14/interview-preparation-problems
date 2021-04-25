package thread.basics;

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

/**
 *
 */
public class ProducerAndConsumerProblem {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<String> buffer = new ArrayList<>();

        Lock lock = new ReentrantLock();
        Condition producer = lock.newCondition();
        Condition consumer = lock.newCondition();

        class Producer implements Callable<String> {

            @Override
            public String call() throws InterruptedException {
                int count = 1;

                for (; count < 5; count++) {
                    System.out.println("Producers call" + count);
                    try {
                        lock.lock();
                        while (buffer.size() == 3)
                            producer.await();

                        buffer.add("" + count);
                        consumer.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
                return count + "";
            }
        }

        class Consumer implements Callable<String> {

            @Override
            public String call() throws InterruptedException {
                int count = 1;
                for (; count < 5; count++) {
                    System.out.println("Consumers call" + count);
                    lock.lock();
                    try {
                        while (buffer.size() == 0)
                            consumer.await();

                        buffer.remove(buffer.size() - 1);
                        producer.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
                return count + "";
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

        List<Callable<String>> list = new ArrayList<>();
        list.addAll(producers);
        list.addAll(consumers);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Future<String>> futures = executorService.invokeAll(list);

        try {
            for (Future future : futures) {
                System.out.println(future.get());
            }
        } finally {
            executorService.shutdown();
        }
    }
}
