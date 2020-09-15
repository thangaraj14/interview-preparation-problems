package multithreading.com.safecabs.passenger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RegisteredPassenger {
    private ConcurrentMap<Integer, Passenger> registeredPassengers;

    public RegisteredPassenger() {

        registeredPassengers = new ConcurrentHashMap<>();
    }

    public void register(Passenger passenger) {

        registeredPassengers.put(passenger.getId(), passenger);
    }

    public boolean isRegistered(Passenger passenger) {

        return registeredPassengers.containsKey(passenger.getId());
    }

}
