package thread.leetcode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class ZeroEvenOdd {

    private int n;
    Semaphore zeroSem, oddSem, evenSem;

    public ZeroEvenOdd(int n) {
        this.n = n;
        zeroSem = new Semaphore(1);
        oddSem = new Semaphore(0);
        evenSem = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            zeroSem.acquire();
            printNumber.accept(0);
            if (i % 2 == 0) {
                evenSem.release();
            } else {
                oddSem.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {

        for (int i = 2; i <= n; i += 2) {
            oddSem.acquire();
            printNumber.accept(i);
            zeroSem.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        
        for (int i = 1; i <= n; i += 2) {
            evenSem.acquire();
            printNumber.accept(i);
            zeroSem.release();
        }
    }
}