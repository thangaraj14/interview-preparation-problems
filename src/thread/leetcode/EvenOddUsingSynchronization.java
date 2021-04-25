package thread.leetcode;

public class EvenOddUsingSynchronization {

    public static void main(String[] args) {

        PrintEvenOdd printEvenOdd = new PrintEvenOdd();

        Thread evenThread = new Thread(new PrintEven(printEvenOdd));
        evenThread.setName("Even Thread");
        Thread oddThread = new Thread(new PrintOdd(printEvenOdd));
        oddThread.setName("Odd Thread");

        evenThread.start();
        oddThread.start();

    }
}

class PrintEven implements Runnable {

    PrintEvenOdd printEvenOdd;

    public PrintEven(PrintEvenOdd printEvenOdd) {
        this.printEvenOdd = printEvenOdd;
    }

    @Override
    public void run() {
        try {
            printEvenOdd.printEven();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PrintOdd implements Runnable {

    PrintEvenOdd printEvenOdd;

    public PrintOdd(PrintEvenOdd printEvenOdd) {
        this.printEvenOdd = printEvenOdd;
    }

    @Override
    public void run() {
        try {
            printEvenOdd.printOdd();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PrintEvenOdd {
    int value = 1;

    void printOdd() throws InterruptedException {

        while (this.value < 20) {
            synchronized (this) {
                if (this.value % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + "--> " + this.value);
                    value++;
                    notify();
                } else {
                    wait();
                }
            }
        }
    }

    void printEven() throws InterruptedException {

        while (this.value < 20) {
            synchronized (this) {
                if (this.value % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + "-->" + this.value);
                    value++;
                    notify();
                } else {
                    wait();
                }
            }
        }
    }
}