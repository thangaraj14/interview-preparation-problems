package multithreading.barberProblem;

import java.util.concurrent.atomic.AtomicInteger;

public class Barber implements Runnable {
    private AtomicInteger id=null;
    private final WaitingRoom waitingRoom;

    public Barber(WaitingRoom waitingRoom, AtomicInteger id) {
        this.waitingRoom = waitingRoom;
        this.id=id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Customer customer = waitingRoom.nextCustomer();

                System.out.println("barber "+ id+" called and shaven customer " + customer);
                customer.callAndShave();
            }

        } catch (InterruptedException e) {
            System.out.println("barber has finished his job");
        }
    }
}
