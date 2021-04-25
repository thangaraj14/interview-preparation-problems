package thread.thread;

class PrintTable {
    public synchronized static void printTable(int n) {
        System.out.println("Table of " + n);
        for (int i = 1; i <= 10; i++) {
            System.out.println(n * i);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class MyThread1 extends Thread {
    public void run() {
        PrintTable.printTable(2);
    }
}

class MyThread2 extends Thread {
    public void run() {
        PrintTable.printTable(5);
    }
}

public class MultiThreadExample {
    public static void main(String args[]) {

        //creating threads.
        MyThread1 t1 = new MyThread1();
        MyThread2 t2 = new MyThread2();

        //start threads.
        t1.start();
        t2.start();
    }
}