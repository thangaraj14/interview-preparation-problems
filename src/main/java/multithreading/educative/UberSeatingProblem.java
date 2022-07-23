package multithreading.educative;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class UberSeatingProblem {
    public static void main(String[] args) throws InterruptedException {
        runTest();
    }

    CyclicBarrier barrier = new CyclicBarrier(4);
    ReentrantLock lock = new ReentrantLock();
    private int republicans = 0;
    private int democrats = 0;
    private Semaphore demsWaiting = new Semaphore(0);
    private Semaphore repubsWaiting = new Semaphore(0);

    void drive() {
        System.out.println("Uber Ride on Its wayyyy... with ride leader " + Thread.currentThread().getName());
    }
    /**
     * Flow goes like this:
     *      step 1: let's say democrat 1 comes, it gets lock on object, increases count, and goes directly to else state,
     *              releases lock and checks for semaphore,
     *              since it's not there it will be in blocked state (ready to proceed once acquiring semaphore)
     *
     *      step 2: let's say 3 more democrats are waiting, fourth thread comes and goes into first if block and releases 3 semaphores,
     *              the 3 previous waiting thread goes and wait's for current thread to join at the barrier, once it comes all goes into seating
     *
     *      step 3: if more than 2 republicans are waiting and current democrat count is 2, this condition releases 1 democrat and 2 republican semaphore
     *              it releases 1 democrat because the current thread is also a democrat, and all will be waiting near the barrier till 4th comes
     */


    void seatDemocrat() throws InterruptedException, BrokenBarrierException {

        boolean rideLeader = false;

        lock.lock();
        System.out.println("###### locked   "+Thread.currentThread().getName());
        democrats++;

        if (democrats == 4) {
            // Seat all the democrats in the Uber ride.
            demsWaiting.release(3);
            democrats -= 4;
            rideLeader = true;
        } else if (democrats == 2 && republicans >= 2) {
            // Seat 2 democrats & 2 republicans
            demsWaiting.release(1);
            repubsWaiting.release(2);
            rideLeader = true;
            democrats -= 2;
            republicans -= 2;
        } else {
            System.out.println("###### unlocking   "+Thread.currentThread().getName() );
            lock.unlock();
            System.out.println("###### State of thread before acquire "+ Thread.currentThread().getName() +"   "+Thread.currentThread().getState());
            demsWaiting.acquire(); // thread will be in blocked state not in wait state
                                  //In the BLOCKED state, a thread is about to enter a synchronized block,
                                 // but there is another thread currently running inside a synchronized block on the same object.
                                // The first thread must then wait for the second thread to exit its block.
            System.out.println("###### State of thread after acquire "+ Thread.currentThread().getName() +"   "+Thread.currentThread().getState());
        }

        seated();
        barrier.await();

        if (rideLeader) {
            drive();
            lock.unlock();
        }
    }

    void seatRepublican() throws InterruptedException, BrokenBarrierException {

        boolean rideLeader = false;
        lock.lock();

        republicans++;

        if (republicans == 4) {
            // Seat all the republicans in the Uber ride.
            repubsWaiting.release(3);
            rideLeader = true;
            republicans -= 4;
        } else if (republicans == 2 && democrats >= 2) {
            // Seat 2 democrats & 2 republicans
            repubsWaiting.release(1);
            demsWaiting.release(2);
            rideLeader = true;
            republicans -= 2;
            democrats -= 2;
        } else {
            lock.unlock();
            repubsWaiting.acquire();
        }

        seated();
        barrier.await();

        if (rideLeader) {
            drive();
            lock.unlock();
        }
    }

    void seated() {
        System.out.println(Thread.currentThread().getName() + "  seated");
    }

    public static void runTest() throws InterruptedException {


        final UberSeatingProblem uberSeatingProblem = new UberSeatingProblem();
        Set<Thread> allThreads = new HashSet<>();

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(() -> {
                try {
                    uberSeatingProblem.seatDemocrat();
                } catch (InterruptedException | BrokenBarrierException ie) {
                    System.out.println("We have a problem");

                }

            });
            thread.setName("Democrat_" + (i + 1));
            allThreads.add(thread);

            Thread.sleep(50);
        }

        for (int i = 0; i < 14; i++) {
            Thread thread = new Thread(() -> {
                try {
                    uberSeatingProblem.seatRepublican();
                } catch (InterruptedException | BrokenBarrierException ie) {
                    System.err.println("We have a problem");

                }
            });
            thread.setName("Republican_" + (i + 1));
            allThreads.add(thread);
            Thread.sleep(20);
        }

        for (Thread t : allThreads) {
            t.start();
        }

        for (Thread t : allThreads) {
            t.join();
        }
    }
}

