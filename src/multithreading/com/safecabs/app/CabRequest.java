package multithreading.com.safecabs.app;


import multithreading.com.safecabs.passenger.Passenger;

public class CabRequest extends Thread {

    private Passenger passenger;
    private CustomCyclicBarrier customCyclicBarrier;

    public CabRequest(CustomCyclicBarrier customCyclicBarrier, Passenger passenger) {
        this.customCyclicBarrier = customCyclicBarrier;
        this.passenger = passenger;
    }

    @Override
    public void run() {
        try {
            System.out.println("Passenger " + passenger.getId() + " requesting cab " + passenger.getGender());

            customCyclicBarrier.await(passenger);

            System.out.println("Passenger " + passenger.getId() + " boarding Cab");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
