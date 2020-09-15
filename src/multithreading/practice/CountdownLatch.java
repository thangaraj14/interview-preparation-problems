package multithreading.practice;

import java.util.concurrent.CountDownLatch;

public class CountdownLatch {

    public static void main(String[] args) {
        final CountDownLatch countdown = new CountDownLatch(10);
        for (int i = 0; i < 10; ++ i){
            Thread t= new Thread() {
                public void run()    {
                    try {
                    doSomething();
                    countdown.countDown();
                    System.out.printf("Waiting on %d other threads.",countdown.getCount());
                        System.out.println();
                        countdown.await();     //waits until everyone reaches this point

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            };
            t.start();
        }
    }

    public static void doSomething() throws InterruptedException {
        Thread.sleep(1000);
    }

    public static void finish(){
        System.out.println("Finished everything");
    }
}


