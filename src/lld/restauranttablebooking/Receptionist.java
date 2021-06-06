package lld.restauranttablebooking;

import lld.airlinereservation.Customer;

import java.util.List;

public class Receptionist extends Employee {

    public boolean createReservation() {
        return false;
    }

    public List<Customer> searchCustomer(String name) {
        return null;
    }
}
