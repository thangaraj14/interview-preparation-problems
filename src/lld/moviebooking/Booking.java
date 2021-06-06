package lld.moviebooking;

import java.util.Date;
import java.util.List;

public class Booking {

    private String bookingNumber;
    private int numberOfSeats;
    private Date createdOn;
    private BookingStatus status;

    private Show show;
    private List<ShowSeat> seats;
    private Payment payment;

    public void makePayment(Payment payment) {
    }

    public void cancel() {
    }

    public void assignSeats(List<ShowSeat> seats) {
    }
}
