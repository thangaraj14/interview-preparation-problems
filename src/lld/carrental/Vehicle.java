package lld.carrental;

import java.util.List;

public abstract class Vehicle {
    private String licenseNumber;
    private String stockNumber;
    private int passengerCapacity;
    private String barcode;
    private boolean hasSunroof;
    private VehicleStatus status;
    private String model;
    private String make;
    private int manufacturingYear;
    private int mileage;

    private List<VehicleLog> log;

    public boolean reserveVehicle() {
        return false;
    }

    public boolean returnVehicle() {
        return false;
    }
}
