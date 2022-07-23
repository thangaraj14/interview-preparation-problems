package multithreading.practice;

import java.util.concurrent.CountDownLatch;

public class CountdownLatch {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countdown = new CountDownLatch(10);
        for (int i = 0; i < 10; ++i) {
            Thread t = new Thread(() -> {
                try {
                    doSomething();
                    countdown.countDown();
                    System.out.printf("Waiting on %d other threads. Thread name %s", countdown.getCount(),Thread.currentThread().getName());
                    System.out.println();
                      //waits until everyone reaches this point

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            });
            t.start();
        }
        countdown.await();
        System.out.println("Main Thread finishing");
    }

    public static void doSomething() throws InterruptedException {
        Thread.sleep(10000);
    }

    public static void finish() {
        System.out.println("Finished everything");
    }
}


