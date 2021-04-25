package thread.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerAndConsumerBlockingQueue {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {

        Thread producerThread = new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }

    private static void producer() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            queue.put(i);
            System.out.println("Produced:" + i);
        }
    }

    private static void consumer() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.out.println("Consumed:" + queue.take());
        }
    }
}