package multithreading.educative;

import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BarberShopProblem {

    final int CHAIRS = 3;
    Semaphore waitForCustomerToEnterShop = new Semaphore(0);
    Semaphore waitForBarberToGetReady = new Semaphore(0);
    Semaphore waitForCustomerToLeaveChair = new Semaphore(0);
    Semaphore waitForBarberToCutHair = new Semaphore(0);
    int waitingCustomers = 0;
    ReentrantLock lock = new ReentrantLock();
    int hairCutsGiven = 0;

    void barber() throws InterruptedException {

        while (true) {
            // wait till a customer enters a shop
            waitForCustomerToEnterShop.acquire();
            // let the customer know barber is ready
            waitForBarberToGetReady.release();

            hairCutsGiven++;
            // close the shop for the day
            if (hairCutsGiven >= 100) return;

            System.out.println("Barber cutting hair..." + hairCutsGiven);
            Thread.sleep(50);

            // let customer thread know, haircut is done
            waitForBarberToCutHair.release();

            // wait for customer to leave the barber chair
            waitForCustomerToLeaveChair.acquire();
        }
    }

    void customerWalksIn() throws InterruptedException {

        lock.lock();
        if (waitingCustomers == CHAIRS) {
            System.out.println("Customer walks out, all waiting waitForCustomerToEnter occupied");
            lock.unlock();
            return;
        }
        waitingCustomers++;
        lock.unlock();

        // Let the barber know, there's atleast 1 customer
        waitForCustomerToEnterShop.release();
        // Wait for the barber to come take you to the salon chair when its your turn
        waitForBarberToGetReady.acquire();

        lock.lock();
        waitingCustomers--;
        lock.unlock();

        // Wait for haircut to complete
        waitForBarberToCutHair.acquire();
        // Leave the barber chair and let barber thread know chair is vacant
        waitForCustomerToLeaveChair.release();
    }

    public static void runTest() throws InterruptedException {

        HashSet<Thread> set = new HashSet<>();
        final BarberShopProblem barberShopProblem = new BarberShopProblem();

        Thread barberThread = new Thread(() -> {
            try {
                barberShopProblem.barber();
            } catch (InterruptedException ie) {

            }
        });
        barberThread.start();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                try {
                    barberShopProblem.customerWalksIn();
                } catch (InterruptedException ie) {

                }
            });
            set.add(t);
        }

        for (Thread t : set) {
            t.start();
        }

        for (Thread t : set) {
            t.join();
        }

        set.clear();
        Thread.sleep(800);

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
                try {
                    barberShopProblem.customerWalksIn();
                } catch (InterruptedException ie) {

                }
            });
            set.add(t);
        }
        for (Thread t : set) {
            t.start();
        }

        barberThread.join();
    }


}
