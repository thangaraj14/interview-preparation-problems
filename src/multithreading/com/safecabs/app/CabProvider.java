package multithreading.com.safecabs.app;


import multithreading.com.safecabs.Constants;
import multithreading.com.safecabs.cab.Cab;
import multithreading.com.safecabs.exceptions.UnRegisteredPassengerException;
import multithreading.com.safecabs.passenger.Passenger;
import multithreading.com.safecabs.passenger.RegisteredPassenger;

import java.util.concurrent.ArrayBlockingQueue;

public class CabProvider {

    private RegisteredPassenger registeredPassenger;

    private ArrayBlockingQueue<Cab> cabQueue = new ArrayBlockingQueue<>(10);

    CustomCyclicBarrier customCyclicBarrier = new CustomCyclicBarrier(Constants.CAB_CAPACITY, new AssignCab(cabQueue));

    public CabProvider(RegisteredPassenger registeredPassenger){

        this.registeredPassenger = registeredPassenger;
    }

    public void requestCab(Passenger passenger) throws UnRegisteredPassengerException {
        if(registeredPassenger.isRegistered(passenger)) {
            provideCab(passenger);
        }else{
            throw new UnRegisteredPassengerException("Passenger "+passenger.getId()+" is not registered");
        }
    }

    public void addNewAvailableCab(Cab cab) {
        //The Cab which is available first should move first, so adding to Queue
        cabQueue.add(cab);
    }

    public void provideCab(Passenger passenger) {
        CabRequest cabRequest = new CabRequest(customCyclicBarrier, passenger);
        cabRequest.start();
    }

}
