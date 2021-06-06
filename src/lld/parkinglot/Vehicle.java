package lld.parkinglot;

public abstract class Vehicle {
    private String licenseNumber;
    private final VehicleType type;
    private ParkingTicket ticket;

    public Vehicle(VehicleType type) {
        this.type = type;
    }

    public void assignTicket(ParkingTicket ticket) {
        this.ticket = ticket;
    }

    public VehicleType getType() {
        // TODO Auto-generated method stub
        return null;
    }
}

