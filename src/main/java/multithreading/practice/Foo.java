package multithreading.practice;


import java.util.concurrent.Semaphore;

/**
 * "Semaphore is a bowl of marbles" - Professor Stark
 * <p>
 * Semaphore is a bowl of marbles (or locks in this case).
 * If you need a marble, and there are none, you wait. You wait until there is one marble and then you take it.
 * If you release(), you will add one marble to the bowl (from thin air).
 * If you release(100), you will add 100 marbles to the bowl. run2.release(); will add one "run2" marble to the "run2 bowl".
 * The thread calling third() will wait until the end of second() when it releases a 'run3' marble.
 * The second() will wait until the end of **first() **when it releases a 'run2' marble.
 * Since first() never acquires anything, it will never wait. There is a forced wait ordering.
 * With semaphores, you can start out with 1 marble or 0 marbles or 100 marbles.
 * A thread can take marbles (up until it's empty) or put many marbles at a time.
 **/
class Foo {
    Semaphore first = new Semaphore(0);
    Semaphore second = new Semaphore(0);
    Semaphore third = new Semaphore(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        first.acquire();
        printFirst.run();
        second.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        first.release();
        second.acquire();
        printSecond.run();
        third.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        first.release();
        third.acquire();
        printThird.run();


    }

    private boolean printOneCompleted = false;
    private boolean printTwoCompleted = false;
    private final Object lock = new Object();

    public  void first(Runnable printFirst, Void tempToAvoidCompilationIssue) throws InterruptedException {
        synchronized(lock) {
            printFirst.run();
            printOneCompleted = true;
            lock.notifyAll();
        }
    }

    public synchronized void second(Runnable printSecond, Void tempToAvoidCompilationIssue) throws InterruptedException {
        synchronized(lock) {
            while (!printOneCompleted) {
                lock.wait();
            }
            printSecond.run();
            printTwoCompleted = true;
            lock.notifyAll();
        }
    }

    public synchronized void third(Runnable printThird, Void tempToAvoidCompilationIssue) throws InterruptedException {
        synchronized(lock) {
            while (!printTwoCompleted) {
               lock.wait();
            }
            printThird.run();
        }
    }

}

class PrintInOrder {
    private Thread createThread(Foo foo, int index) {
        return new Thread(() -> {
            try {
                switch (index) {
                    case 1:
                        foo.first(() -> System.out.print("first"));
                        break;
                    case 2:
                        foo.second(() -> System.out.print("second"));
                        break;
                    case 3:
                        foo.third(() -> System.out.print("third"));
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void test0() throws InterruptedException {
        Foo foo = new Foo();
        int[] input = {1, 2, 3};
        for (int i : input) {
            Thread thread = createThread(foo, i);
            thread.start();
        }
    }

    public void test1() throws InterruptedException {
        Foo foo = new Foo();
        int[] input = {1, 3, 2};
        for (int i : input) {
            Thread thread = createThread(foo, i);
            thread.start();
        }
    }
}