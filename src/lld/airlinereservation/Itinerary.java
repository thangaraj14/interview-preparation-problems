package lld.airlinereservation;

import java.util.Date;
import java.util.List;

public class Itinerary {
    private String customerId;
    private Airport startingAirport;
    private Airport finalAirport;
    private Date creationDate;
    private List<FlightReservation> reservations;

    public List<FlightReservation> getReservations() {
        return null;
    }

    public boolean makeReservation() {
        return false;
    }

    public boolean makePayment() {
        return false;
    }
}