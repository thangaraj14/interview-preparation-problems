package thread.basics;

public class InterThreadCommunication {

    public static void main(String[] args) throws InterruptedException {

        Customer c = new Customer();
        Thread t1 = new Thread(c::withDraw);

        Thread t2 = new Thread(c::deposit);

        t1.start();
        t2.start();

        Thread.sleep(2000);
        System.out.println(c.amount);
    }
}

class Customer {
    int amount = -1;

    public synchronized void withDraw() {
        System.out.println("going to withdraw");
        if (amount < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        amount = amount - 100;
    }

    public synchronized void deposit() {
        System.out.println("going to deposit");
        amount = 500;
        notify();
    }
}