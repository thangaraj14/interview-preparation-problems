package lld.restauranttablebooking;

import lld.airlinereservation.Customer;
import lld.onlineshopping.Notification;

import java.util.Date;
import java.util.List;

public class Reservation {
    private int reservationID;
    private Date timeOfReservation;
    private int peopleCount;
    private ReservationStatus status;
    private String notes;
    private Date checkinTime;
    private Customer customer;

    private Table[] tables;
    private List<Notification> notifications;

    public boolean updatePeopleCount(int count) {
        return false;
    }
}