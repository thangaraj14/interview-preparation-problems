package multithreading.thread;
public class PrintOddEvenByTwoThreads {
    static int number = 1;
    static Thread odd;
    static Thread even;
    static int max = 10;

    static class OddThread extends Thread {
        @Override
        public void run() {
            while (number <= max) {
                if (number % 2 == 1) {
                    System.out.println(Thread.currentThread() + "" + number++);
                } else {

                    synchronized (odd) {
                        synchronized (even) {
                            even.notify();
                        }
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    static class EvenThread extends Thread {
        @Override
        public void run() {
            while (number <= max) {
                if (number % 2 == 0) {
                    System.out.println(Thread.currentThread() + "" + number++);
                } else {

                    synchronized (even) {
                        synchronized (odd) {
                            odd.notify();
                        }
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        odd = new OddThread();
        even = new EvenThread();
        odd.start();
        even.start();
    }
}