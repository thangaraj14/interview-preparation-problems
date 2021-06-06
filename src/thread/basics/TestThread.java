package thread.basics;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + "::" + i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(runnable);
        t1.setName("T1");
        Thread t2 = new Thread(runnable);
        t2.setName("T2");

        Callable<Integer> callable = () -> 1;

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(callable);

        t1.start();
        //        t1.join();
        //        Thread.sleep(1000);
        t2.start();
    }
}
