package lld.carrental;

import java.util.Date;
import java.util.List;

public class VehicleReservation {
    private String reservationNumber;
    private Date creationDate;
    private ReservationStatus status;
    private Date dueDate;
    private Date returnDate;
    private String pickupLocationName;
    private String returnLocationName;

    private int customerID;
    private Vehicle vehicle;
    private Bill bill;
    private List<AdditionalDriver> additionalDrivers;
    private List<Notification> notifications;
    private List<RentalInsurance> insurances;
    private List<Equipment> equipments;
    private List<Service> services;

    public static VehicleReservation fectchReservationDetails(String reservationNumber) {
        return null;
    }

    public List<Passenger> getAdditionalDrivers() {
        return null;
    }
}

class Bill {
}

class Notification {
}

class RentalInsurance {
}

class Equipment {
}

class Service {
}

class Passenger {
}