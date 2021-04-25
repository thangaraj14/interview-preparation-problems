package thread.leetcode;

import java.util.concurrent.Semaphore;

class ProducerConsumerSemaphore {

    int item;

    // semCon initialized with 0 permits
    // to ensure put() executes first
    static Semaphore semCon = new Semaphore(0);

    static Semaphore semProd = new Semaphore(1);

    // to get an item from buffer
    void get() {
        try {
            // Before consumer can consume an item,
            // it must acquire a permit from semCon
            semCon.acquire();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }

        // consumer consuming an item
        System.out.println("Consumer consumed item : " + item);

        // After consumer consumes the item,
        // it releases semProd to notify producer
        semProd.release();
    }

    // to put an item in buffer
    void put(int item) {
        try {
            // Before producer can produce an item,
            // it must acquire a permit from semProd
            semProd.acquire();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }

        // producer producing an item
        this.item = item;

        System.out.println("Producer produced item : " + item);

        // After producer produces the item,
        // it releases semCon to notify consumer
        semCon.release();
    }
}

class Producer implements Runnable {

    ProducerConsumerSemaphore producerConsumerSemaphore;

    Producer(ProducerConsumerSemaphore producerConsumerSemaphore) {
        this.producerConsumerSemaphore = producerConsumerSemaphore;
        new Thread(this, "Producer").start();
    }

    public void run() {
        for (int i = 0; i < 5; i++)
            producerConsumerSemaphore.put(i);
    }
}

class Consumer implements Runnable {
    ProducerConsumerSemaphore producerConsumerSemaphore;

    Consumer(ProducerConsumerSemaphore producerConsumerSemaphore) {
        this.producerConsumerSemaphore = producerConsumerSemaphore;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        for (int i = 0; i < 5; i++)
            producerConsumerSemaphore.get();
    }
}

class PC {
    public static void main(String[] args) {

        ProducerConsumerSemaphore producerConsumerSemaphore = new ProducerConsumerSemaphore();

        new Consumer(producerConsumerSemaphore);
        new Producer(producerConsumerSemaphore);
    }
} 
