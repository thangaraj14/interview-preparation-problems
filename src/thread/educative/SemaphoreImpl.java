package thread.educative;

class SemaphoreImpl {
    
    public static void main(String[] args) throws InterruptedException {

        final CountingSemaphore cs = new CountingSemaphore(2);

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    cs.acquire();
                    //                    Thread.sleep(5000);
                    System.out.println("Ping " + i + " " + Thread.currentThread().getName());
                }
            } catch (InterruptedException ie) {

            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    cs.release();
                    //                    Thread.sleep(5000);
                    System.out.println("Pong " + i + " " + Thread.currentThread().getName());
                } catch (InterruptedException ie) {

                }
            }
        });

        t2.start();
        t1.start();
        t1.join();
        t2.join();
    }
}

class CountingSemaphore {

    int usedPermits = 0;
    int maxCount;

    public CountingSemaphore(int count) {
        this.maxCount = count;

    }

    public synchronized void acquire() throws InterruptedException {
        while (usedPermits == maxCount) {
            System.out.println(Thread.currentThread().getName() + " acquire");
            wait();
        }

        usedPermits++;
        notify();

    }

    public synchronized void release() throws InterruptedException {
        while (usedPermits == 0) {
            System.out.println(Thread.currentThread().getName() + " release");
            wait();
        }

        usedPermits--;
        notify();
    }
}