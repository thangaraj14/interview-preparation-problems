package lld.airlinereservation;

import java.util.Date;

public class FlightInstance {
    private Date departureTime;
    private String gate;
    private FlightStatus status;

    public boolean cancel() {
        return true;
    }

    public void updateStatus(FlightStatus status) {
    }
}
