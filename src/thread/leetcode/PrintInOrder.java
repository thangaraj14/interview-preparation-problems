package thread.leetcode;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * https://hezhigang.github.io/2019/08/08/LeetCode-Concurrency-Print-in-Order/
 */
public class PrintInOrder {

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();

        FirstOrganizer firstOrganizer = new FirstOrganizer(foo);
        SecondOrganizer secondOrganizer = new SecondOrganizer(foo);
        ThirdOrganizer thirdOrganizer = new ThirdOrganizer(foo);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(secondOrganizer);
        executorService.submit(firstOrganizer);
        executorService.submit(thirdOrganizer);
        executorService.shutdown();
    }
}

class FirstOrganizer implements Callable<String> {

    Foo foo;

    public FirstOrganizer(Foo foo) {
        this.foo = foo;
    }

    @Override
    public String call() throws Exception {
        foo.first(() -> System.out.println("first"));
        return "";
    }
}

class SecondOrganizer implements Callable<String> {

    Foo foo;

    public SecondOrganizer(Foo foo) {
        this.foo = foo;
    }

    @Override
    public String call() throws Exception {
        foo.second(() -> System.out.println("second"));
        return "";
    }
}

class ThirdOrganizer implements Callable<String> {

    Foo foo;

    public ThirdOrganizer(Foo foo) {
        this.foo = foo;
    }

    @Override
    public String call() throws Exception {
        foo.third(() -> System.out.println("third"));
        return "";
    }
}

class Foo {

    Semaphore first = new Semaphore(0);
    Semaphore second = new Semaphore(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        first.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        first.acquire();
        printSecond.run();
        second.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        second.acquire();
        printThird.run();
    }
}