package thread.leetcode;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class OddEvenSemaphore {

    public static void main(String[] args) {

        PrinterSemaphore print = new PrinterSemaphore();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(new OddSemaphore(print));
        executorService.submit(new EvenSemaphore(print));
        executorService.shutdown();
    }
}

class OddSemaphore implements Callable {

    PrinterSemaphore print;

    public OddSemaphore(PrinterSemaphore p) {
        this.print = p;
    }

    @Override
    public String call() {
        for (int i = 1; i < 20; i += 2) {
            print.printOdd(i);
        }
        return "";
    }
}

class EvenSemaphore implements Callable {

    PrinterSemaphore print;

    public EvenSemaphore(PrinterSemaphore p) {
        this.print = p;
    }

    @Override
    public String call() {
        for (int i = 2; i < 20; i += 2) {
            print.printEven(i);
        }
        return "";
    }
}

class PrinterSemaphore {
    Semaphore semOdd = new Semaphore(1);
    Semaphore semEven = new Semaphore(0);

    public void printEven(int val) {
        try {
            semEven.acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " value :" + val);
        semOdd.release();
    }

    public void printOdd(int val) {
        try {
            semOdd.acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " value :" + val);
        semEven.release();

    }
}