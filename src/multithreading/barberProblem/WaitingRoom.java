package multithreading.barberProblem;

import java.util.concurrent.ArrayBlockingQueue;

public class WaitingRoom {

    private final ArrayBlockingQueue<Customer> waitingCustomers;

    public WaitingRoom(int capacity) {
        waitingCustomers = new ArrayBlockingQueue<>(capacity);
    }

    public void takeASeat(Customer customer) throws InterruptedException {
        waitingCustomers.put(customer);
    }

    public Customer nextCustomer() throws InterruptedException {
        return waitingCustomers.take();
    }

}
