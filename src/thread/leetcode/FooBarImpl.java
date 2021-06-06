package thread.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class FooBar {

    private final int n;
    Semaphore fooSemaphore;
    Semaphore barSemaphore;

    public FooBar(int n) {
        this.n = n;
        fooSemaphore = new Semaphore(1);
        barSemaphore = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fooSemaphore.acquire();
            printFoo.run();
            barSemaphore.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            barSemaphore.acquire();
            printBar.run();
            fooSemaphore.release();
        }
    }
}

class FooOrganizer implements Runnable {
    FooBar fooBar;

    public FooOrganizer(FooBar fooBar) {
        this.fooBar = fooBar;
    }

    public void run() {
        try {
            fooBar.foo(() -> System.out.println("Foo"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BarOrganizer implements Runnable {
    FooBar fooBar;

    public BarOrganizer(FooBar fooBar) {
        this.fooBar = fooBar;
    }

    public void run() {
        try {
            fooBar.bar(() -> System.out.println("Bar"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class FooBarImpl {
    
    public static void main(String[] args) {
        FooBar fooBar = new FooBar(5);

        FooOrganizer fooOrganizer = new FooOrganizer(fooBar);
        BarOrganizer barOrganizer = new BarOrganizer(fooBar);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(barOrganizer);
        executorService.submit(fooOrganizer);
        executorService.shutdown();
    }
}