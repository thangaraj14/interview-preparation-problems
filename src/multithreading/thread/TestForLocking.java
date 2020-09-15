package thread;

import java.util.Date;

public class TestForLocking {
    public static void main(String[] args) throws Exception {
        testCuncurrency();
    }

    private static void testCuncurrency() throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(new WaitTester(lock));
        Thread t2 = new Thread(new WaitTester(lock));
        t1.start();
        t2.start();
        Thread.sleep(15 * 1000);
        synchronized (lock) {
            System.out.println("Time: " + new Date().toString()+ ";" + "Notifying all");
            lock.notifyAll();
        }
    }

    private static class WaitTester implements Runnable {
        private Object lock;
        public WaitTester(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                synchronized (lock) {
                    System.out.println(getTimeAndThreadName() + ":only one thread can be in synchronized block");
                    Thread.sleep(5 * 1000);

                    System.out.println(getTimeAndThreadName() + ":thread goes into waiting state and releases the lock");
                    lock.wait();

                    System.out.println(getTimeAndThreadName() + ":thread is awake and have reacquired the lock");

                    System.out.println(getTimeAndThreadName() + ":syncronized block have finished");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static String getTimeAndThreadName() {
        return "Time: " + new Date().toString() + ";" + Thread.currentThread().getName();
    }
}