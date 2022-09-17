package multithreading.educative;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DemonstrationThreadLocal {
    public static void main(String args[]) throws Exception {

        usingThreads();
        usingSingleThreadPool();
        usingMultiThreadsPool();
    }

    static void usingThreads() throws Exception {

        Counter counter = new Counter();
        Thread[] tasks = new Thread[100];

        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 100; j++)
                    counter.increment();
            });
            tasks[i] = t;
            t.start();
        }

        for (int i = 0; i < 100; i++) {
            tasks[i].join();
        }

        System.out.println(counter.counter.get());
    }

    @SuppressWarnings("unchecked")
    static void usingSingleThreadPool() throws Exception {

        Counter counter = new Counter();
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer>[] tasks = new Future[100];

        for (int i = 0; i < 100; i++) {
            tasks[i] = es.submit(() -> {
                for (int j = 0; j < 100; j++)
                    counter.increment();

                return counter.counter.get();
            });
        }

        System.out.println(tasks[99].get());

        es.shutdown();
    }

    @SuppressWarnings("unchecked")
    static void usingMultiThreadsPool() throws Exception {

        Counter counter = new Counter();
        ExecutorService es = Executors.newFixedThreadPool(20);
        Future<Integer>[] tasks = new Future[100];

        for (int i = 0; i < 100; i++) {
            tasks[i] = es.submit(() -> {
                for (int j = 0; j < 100; j++)
                    counter.increment();

                return counter.counter.get();
            });
        }

        System.out.println(tasks[99].get());

        es.shutdown();
    }


    static class Counter {

        ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 0);

        public Counter() {
            counter.set(0);
        }

        void increment() {
            counter.set(counter.get() + 1);
        }
    }
}
