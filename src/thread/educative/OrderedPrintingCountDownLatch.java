package thread.educative;

import java.util.concurrent.CountDownLatch;

class OrderedPrinting {

    CountDownLatch latch1;
    CountDownLatch latch2;

    public OrderedPrinting() {
        latch1 = new CountDownLatch(1);
        latch2 = new CountDownLatch(1);
    }

    public void printFirst() {
        System.out.println("First");
        latch1.countDown();
    }

    public void printSecond() throws InterruptedException {
        latch1.await();
        System.out.println("Second");
        latch2.countDown();
    }

    public void printThird() throws InterruptedException {
        latch2.await();
        System.out.println("Third");
    }
}

public class OrderedPrintingCountDownLatch {
    public static void main(String[] args) {
        OrderedPrinting obj = new OrderedPrinting();

        Thread t1 = new Thread(obj::printFirst);
        Thread t2 = new Thread(() -> {
            try {
                obj.printSecond();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                obj.printThird();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t3.start();
        t2.start();

    }
}